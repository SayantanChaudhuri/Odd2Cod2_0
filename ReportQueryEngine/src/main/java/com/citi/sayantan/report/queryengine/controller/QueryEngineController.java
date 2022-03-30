package com.citi.sayantan.report.queryengine.controller;

import com.citi.sayantan.report.queryengine.service.QueryEngineService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class QueryEngineController {

    @Autowired
    private QueryEngineService queryEngineService;

    @PostMapping("/{soeId}/{dbName}/execute-query")
    public ObjectNode executeQuery(@PathVariable String soeId, @PathVariable String dbName, @RequestBody String nativeQuery) {
        return this.queryEngineService.getNativeQueryResultSet(nativeQuery, soeId, dbName);
    }

    @PostMapping(value = "/{soeId}/download-csv", produces = "text/csv")
    @SneakyThrows
    public ResponseEntity<InputStreamResource> generateCSV(@PathVariable String soeId, @RequestBody ObjectNode rootNode) {
        String fileName = String.format("%s_%s.csv", soeId, String.valueOf(System.nanoTime()));
        log.info("fileName :: {}", fileName);
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(this.queryEngineService.generateCSV(rootNode)));
    }
}
