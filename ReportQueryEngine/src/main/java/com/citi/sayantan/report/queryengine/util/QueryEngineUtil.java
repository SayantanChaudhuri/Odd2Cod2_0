package com.citi.sayantan.report.queryengine.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.io.StringReader;
import java.util.*;

@Slf4j
public class QueryEngineUtil {

    /*@SneakyThrows
    public static List<String> getTableNames(String nativeQuery) {

        Select statement = (Select) CCJSqlParserUtil.parse(nativeQuery);
        TablesNamesFinder finder = new TablesNamesFinder();

        return finder.getTableList(statement);
    }*/

    @SneakyThrows
    public static Map<String, Set<String>> getTablesAndFieldNames(String nativeQuery) {
        log.info("nativeQuery :: {}", nativeQuery);
        Map<String, Set<String>> tablesColumnMap = new HashMap<>();
        try {
            CCJSqlParserManager parseSql = new CCJSqlParserManager();
//            statement = "select 0 as c1,D1.c45 as c2,D1.c44 as c3,D1.c43 as c4,D1.c42 as c5,D1.c41 as c6,D1.c40 as c7,D1.c46 as c8,D1.c47 as c9,D1.c39 as c10,D1.c38 as c11,D1.c48 as c12,D1.c49 as c13,D1.c50 as c14,D1.c51 as c15,D1.c52 as c16,D1.c53 as c17,D1.c54 as c18,D1.c37 as c19,D1.c36 as c20,D1.c35 as c21,D1.c34 as c22,D1.c33 as c23,D1.c32 as c24,D1.c31 as c25,D1.c30 as c26,D1.c29 as c27,D1.c28 as c28,D1.c27 as c29,D1.c26 as c30,D1.c25 as c31,D1.c24 as c32,D1.c23 as c33,D1.c22 as c34,D1.c21 as c35,D1.c20 as c36,D1.c19 as c37,D1.c18 as c38,D1.c17 as c39,D1.c55 as c40,D1.c56 as c41,D1.c16 as c42,D1.c15 as c43,D1.c14 as c44,D1.c13 as c45,D1.c12 as c46,D1.c11 as c47,D1.c10 as c48,D1.c9 as c49,D1.c8 as c50,D1.c57 as c51,D1.c57 as c52,D1.c7 as c53,D1.c6 as c54,D1.c5 as c55,D1.c4 as c56,D1.c3 as c57,D1.c2 as c58,D1.c1 as c59 from (select sum(TABLE129.USD_ACTIVITY_AMOUNT) as c1, sum(ifnull(TABLE129.ENTERED_DEBIT , 0) - ifnull(TABLE129.ENTERED_CREDIT , 0)) as c2,sum(TABLE129.ENTERED_DEBIT) as c3,sum(TABLE129.ENTERED_CREDIT) as c4,sum(ifnull(TABLE129.ACCOUNTED_DEBIT , 0) - ifnull(TABLE129.ACCOUNTED_CREDIT , 0)) as c5,sum(TABLE129.ACCOUNTED_DEBIT) as c6,sum(TABLE129.ACCOUNTED_CREDIT) as c7,TABLE129.trading_partner_name_tp as c8,TABLE129.currency_code as c9,TABLE129.rt_LINE_NUM as c10,TABLE129.rt_LINE_DESCRIPTION as c11,TABLE129.concatenated_segments as c12,TABLE129.rt_LINES_ATTRIBUTE9 as c13,TABLE129.rt_LINES_ATTRIBUTE8 as c14,TABLE129.rt_LINES_ATTRIBUTE7 as c15,TABLE129.rt_LINES_ATTRIBUTE6 as c16,TABLE129.rt_LINES_ATTRIBUTE5 as c17,TABLE129.rt_LINES_ATTRIBUTE4 as c18,TABLE129.rt_LINES_ATTRIBUTE3 as c19,TABLE129.rt_LINES_ATTRIBUTE20 as c20,TABLE129.rt_LINES_ATTRIBUTE2 as c21,TABLE129.rt_LINES_ATTRIBUTE19 as c22,TABLE129.rt_LINES_ATTRIBUTE18 as c23,TABLE129.rt_LINES_ATTRIBUTE17 as c24,TABLE129.rt_LINES_ATTRIBUTE16 as c25,TABLE129.rt_lines_attribute15 as c26,TABLE129.rt_lines_attribute14 as c27,TABLE129.rt_lines_attribute13 as c28,TABLE129.rt_lines_attribute12 as c29,TABLE129.rt_lines_attribute11 as c30,TABLE129.rt_LINES_ATTRIBUTE10 as c31,TABLE129.rt_LINES_ATTRIBUTE1 as c32,TABLE129.rt_SOURCE_NAME as c33,TABLE129.rt_CATEGORY_NAME as c34,TABLE129.rt_HEADER_ID as c35,TABLE129.rt_HEADER_NAME as c36,TABLE129.rt_CREATED_BY as c37,TABLE129.cost_center_name_cc as c38,TABLE129.company_code_name_lb as c39,TABLE129.batch_posted_date as c40,TABLE129.BATCH_NAME as c41,TABLE129.BATCH_ID as c42,TABLE129.batch_description as c43,TABLE129.account_name_acc as c44,T1334971.description as c45,T1330596.associated_bu as c46,T1330596.associated_le as c47,T9862411.cube_period as c48,T9862411.ledger_period_name as c49,T9862411.mk_period_num as c50,T9862411.qtr as c51,T9862411.mk_period_year as c52,T1338224.name as c53,T1354092.employee_name as c54,T1479416.attribute57 as c55,T1479416.attribute58 as c56,T9862411.gaap_month_num as c57 from ( au_mk_ledgers T1338224 /* DIM_mk_LEDGER */  inner join ( (au_drm_hier_company_code T1330596 /* DIM_COMPANY_CODE_HIER */  inner join (au_KETOL_period_dimension_v T9862411 /* DIM_KETOL_PERIOD */  inner join ( au_drm_hier_account T1334971 /* DIM_ACCOUNT_HIER */  inner join sld_mk_journals TABLE129 /* FACT_mk_DETAILS */  On T1334971.join_computed = TABLE129.join_account_computed) On T9862411.mk_period_year = TABLE129.period_year and T9862411.source_erp = TABLE129.source_system_name and T9862411.ledger_id = TABLE129.LEDGER_ID and T9862411.mk_period_name = TABLE129.period_name) On T1330596.join_fact_computed = TABLE129.join_company_code_computed) left outer join  au_fnd_user T1354092 /* DIM_HEADERS_CREATED_BY */  On TABLE129.rt_header_created_by = T1354092.user_id and TABLE129.source_system_name = T1354092.source_system_name) On T1338224.ledger_id = TABLE129.LEDGER_ID and T1338224.source_system_name = TABLE129.source_system_name) left outer join au_mk_rt_lines_addl_dffs T1479416 /* DIM_mk_rt_LINES_ADDL_DFFS */  On TABLE129.rt_HEADER_ID = T1479416.rt_header_id and TABLE129.rt_LINE_NUM = T1479416.rt_line_num and TABLE129.period_year = T1479416.period_year and TABLE129.source_system_name = T1479416.source_system_name  where  ( T1330596.drm_type = 'BASE' and T1334971.drm_type = 'E' and T1338224.name = 'USD Primary Ledger' and TABLE129.company_code_name_lb = 'LB_GIQ3' and TABLE129.account_name_acc = 'AC_1510301000' and (T9862411.ledger_period_name = 'OCT-19' or T9862411.ledger_period_name = 'SEP-19') )  group by T1330596.associated_le, T1330596.associated_bu, T1334971.description, T1338224.name, T9862411.mk_period_year, T9862411.mk_period_num, T9862411.cube_period, T9862411.qtr, T9862411.gaap_month_num, T9862411.ledger_period_name, TABLE129.rt_HEADER_ID, TABLE129.rt_LINE_NUM, TABLE129.rt_LINES_ATTRIBUTE1, TABLE129.rt_LINES_ATTRIBUTE2, TABLE129.rt_LINES_ATTRIBUTE3, TABLE129.rt_LINES_ATTRIBUTE4, TABLE129.rt_LINES_ATTRIBUTE5, TABLE129.rt_LINES_ATTRIBUTE6, TABLE129.rt_LINES_ATTRIBUTE7, TABLE129.rt_LINES_ATTRIBUTE8, TABLE129.rt_LINES_ATTRIBUTE9, TABLE129.rt_LINES_ATTRIBUTE10, TABLE129.rt_lines_attribute11, TABLE129.rt_lines_attribute12, TABLE129.rt_lines_attribute13, TABLE129.rt_lines_attribute14, TABLE129.rt_lines_attribute15, TABLE129.rt_LINES_ATTRIBUTE16, TABLE129.rt_LINES_ATTRIBUTE17, TABLE129.rt_LINES_ATTRIBUTE18, TABLE129.rt_LINES_ATTRIBUTE19, TABLE129.rt_LINES_ATTRIBUTE20, TABLE129.currency_code, TABLE129.concatenated_segments, TABLE129.rt_CATEGORY_NAME, TABLE129.batch_posted_date, TABLE129.batch_description, TABLE129.BATCH_ID, TABLE129.rt_HEADER_NAME, TABLE129.BATCH_NAME, TABLE129.rt_CREATED_BY, TABLE129.rt_SOURCE_NAME, TABLE129.company_code_name_lb, TABLE129.account_name_acc, TABLE129.trading_partner_name_tp, TABLE129.cost_center_name_cc, TABLE129.rt_LINE_DESCRIPTION, T1354092.employee_name, T1479416.attribute57, T1479416.attribute58) D1 order by c17, c51, c10, c8, c9, c3, c2, c50, c11, c48, c47, c49, c23, c19, c18, c7, c6, c5, c24, c35, c37, c38, c39, c42, c43, c44, c45, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c36, c40, c41, c46, c22, c4, c21, c20, c16, c15, c14 limit 10000001";
            Select select = (Select) parseSql.parse(new StringReader(nativeQuery));
            PlainSelect plain = (PlainSelect) select.getSelectBody();
            List<SelectItem> selectitems = plain.getSelectItems();
            for (int index = 0; index < selectitems.size(); index++) {
                log.info("selectitem :: {}", selectitems.get(index));

                if (selectitems.get(index) instanceof AllColumns || selectitems.get(index) instanceof AllTableColumns) {
//                    AllColumns allColumns = (AllColumns) selectitems.get(index);

                    Select selectAll = (Select) CCJSqlParserUtil.parse(nativeQuery);

                    TablesNamesFinder tablesNamesFinder = new TablesNamesFinder() {
                        @Override
                        public void visit(PlainSelect plainSelect) {
                            System.out.println("plainselect -> " + plainSelect.toString());
                            for (SelectItem item : plainSelect.getSelectItems()) {
                                System.out.println("item :: " + item.toString());
                            }
                            super.visit(plainSelect);
                        }
                    };

                    tablesNamesFinder.getTableList(selectAll).stream().forEach(tableName -> addTableColumn(tablesColumnMap, tableName, null));

                } else {

                    Expression expressionIns = ((SelectExpressionItem) selectitems.get(index)).getExpression();
                    if (expressionIns instanceof Column) {
                        Column columnInstance = (Column) expressionIns;
                        String tableName = columnInstance.getTable().getName();
                        String columnName = columnInstance.getColumnName();
                        log.info("Table Name :: {}", tableName);
                        log.info("Column  Name :: {}", columnName);
                        addTableColumn(tablesColumnMap, tableName, columnName);
                    }
                } /*else if (expressionIns instanceof Function) {
                Function functionIns = (Function) expressionIns;
                System.out.println("Function Name :: " + functionIns.getName());
            }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return tablesColumnMap;
    }

    private static void addTableColumn(Map<String, Set<String>> tablesColumnMap, String tableName, String columnName) {

        if (tablesColumnMap.containsKey(tableName)) {
            Set columnSet = tablesColumnMap.get(tableName.toUpperCase());
            if (columnSet != null)
                columnSet.add(columnName);
            tablesColumnMap.put(tableName, columnSet);
        } else {
            Set<String> columnSet = new HashSet<>();
            if (columnName != null)
                columnSet.add(columnName.toUpperCase());
            tablesColumnMap.put(tableName, columnSet);
        }
    }
}
