package com.citi.sayantan.report.tablesinfo.controller;

import com.citi.sayantan.report.tablesinfo.domain.TableDetails;
import com.citi.sayantan.report.tablesinfo.domain.MaskFieldsInfo;
import com.citi.sayantan.report.tablesinfo.service.TableInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TableInfoController {

    @Autowired
    public TableInfoService tableInfoService;

    @GetMapping("/db/{dbName}/tables/{tableNames}")
    public List<MaskFieldsInfo> getTableDetails(@PathVariable String dbName, @PathVariable List<String> tableNames) {
        log.info("dbName :: {}", dbName);
        log.info("tableNames :: {}", tableNames);

        return this.tableInfoService.getTableDetailsByDbName(dbName, tableNames);
    }

    @PostMapping("/db/{dbName}/table")
    public TableDetails upsertTableDetails(@RequestBody TableDetails tableDetails) {
        return this.tableInfoService.upsertTableDetails(tableDetails);
    }
}
