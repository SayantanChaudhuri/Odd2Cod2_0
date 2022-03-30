package com.citi.sayantan.report.queryengine.service;

import com.citi.sayantan.report.queryengine.client.AuditClient;
import com.citi.sayantan.report.queryengine.client.TableClient;
import com.citi.sayantan.report.queryengine.client.UserEntitlement;
import com.citi.sayantan.report.queryengine.domain.AuditDetail;
import com.citi.sayantan.report.queryengine.domain.MaskFieldsInfo;
import com.citi.sayantan.report.queryengine.util.QueryEngineUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Setter
@Slf4j
public class QueryEngineService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    AuditClient auditClient;

    @Autowired
    UserEntitlement userEntitlement;

    @Autowired
    TableClient tableClient;

    public ObjectNode getNativeQueryResultSet(String nativeQuery, String soeId, String dbName) {

        Set<String> tableNames = null;
        try {

            Map<String, Set<String>> tableColumnMap = QueryEngineUtil.getTablesAndFieldNames(nativeQuery);

            tableNames = tableColumnMap.keySet();

        } catch (Exception e) {
            throw new RuntimeException("Not a valid statement");
        }

        log.info("Table names :: {}", tableNames);
        Set<String> blockedTables = new HashSet<>() {{
            addAll(userEntitlement.getBlockedTables(soeId));
        }};

        log.info("blockedTables :: {}", blockedTables);

        if (!this.isUserAuthorized(tableNames, blockedTables))
            throw new RuntimeException("User " + soeId + " is not authorized to execute the statement");

        List<MaskFieldsInfo> maskFieldsInfoList = this.tableClient.getTableDetails(dbName, tableNames);

        log.info("maskFieldsInfoList :: {}", maskFieldsInfoList);

        Set<String> maskedColumns = null;

        try {
            maskedColumns = maskFieldsInfoList.stream().map(MaskFieldsInfo::getMaskedFields).collect(Collectors.toSet());
            log.info("maskedColumns :: {}", maskedColumns);
        } catch (Exception e) {
        }


        Query localQuery = entityManager.createNativeQuery(nativeQuery, Tuple.class);
        List<Tuple> results = localQuery.getResultList();
        AuditDetail auditDetail = AuditDetail.builder().query(nativeQuery).soeId(soeId).createdTime(LocalDateTime.now()).build();
        log.info("auditDetail :: {}", auditDetail);
        this.auditClient.sendAuditDetail(auditDetail);
        return toJson(results, maskedColumns);
    }

    private boolean isUserAuthorized(Set<String> executingTables, Set<String> blockedTables) {
        if (blockedTables == null || blockedTables.isEmpty())
            return true;

        Set<String> tobeCheckedList = new HashSet<>() {{
            addAll(executingTables);
        }};
        tobeCheckedList.removeAll(blockedTables);

        return tobeCheckedList.size() == executingTables.size();
    }

    private static ObjectNode toJson(List<Tuple> results, Set<String> maskedColumns) {

        log.info("Tuple size :: {}", results.size());

        ObjectMapper mapper = new ObjectMapper();

        ArrayNode resultArrayNode = mapper.createArrayNode();

        Set<String> headerSet = new LinkedHashSet<>();

        for (Tuple tuple : results) {
            List<TupleElement<?>> cols = tuple.getElements();

            ObjectNode node = mapper.createObjectNode();

            for (TupleElement col : cols) {
                log.info("col :: {}", col.getAlias());
                String columnName = col.getAlias();
                String columnValue = tuple.get(col.getAlias()).toString();
                node.put(columnName, getMaskedValue(columnName, columnValue, maskedColumns));
                headerSet.add(col.getAlias());
            }
            resultArrayNode.add(node);
        }

        /*results.stream().parallel().forEach(tuple -> {
            List<TupleElement<?>> cols = tuple.getElements();
            ObjectNode node = mapper.createObjectNode();

            cols.stream().parallel().forEach(col -> {
                node.put(col.getAlias(), tuple.get(col.getAlias()).toString());
                headerSet.add(col.getAlias());
            });

            resultArrayNode.add(node);
        });*/

        ArrayNode headerArrayNode = mapper.createArrayNode();
        headerSet.stream().forEach(header -> {
            headerArrayNode.add(header);
        });

        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("totalRecordCount", results.size());
        rootNode.set("headers", headerArrayNode);
        rootNode.set("result", resultArrayNode);

        return rootNode;
    }

    private static String getMaskedValue(String columnName, String value, Set<String> maskedColumns) {
        log.info("columnName :: {}", columnName);
        log.info("value :: {}", value);
        log.info("maskedColumns :: {}", maskedColumns);
        if (maskedColumns != null && !maskedColumns.isEmpty() && value != null && !value.isEmpty() && maskedColumns.contains(columnName)) {
            log.info("replacing...");
            return value.replaceAll("[\\s\\S]", "*");
        }

        return value;
    }

    @SneakyThrows
    public ByteArrayInputStream generateCSV(ObjectNode rootNode) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream, true);

        Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode resultNode = rootNode.get("result");
        JsonNode firstObject = resultNode.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(printWriter, resultNode);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
