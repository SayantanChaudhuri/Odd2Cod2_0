package com.citi.sayantan.report.queryengine.client;

import com.citi.sayantan.report.queryengine.domain.MaskFieldsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@FeignClient(value = "tableClient", url = "http://localhost:8002/api/v1")
public interface TableClient {

    @GetMapping("/db/{dbName}/tables/{tableNames}")
    List<MaskFieldsInfo> getTableDetails(@PathVariable String dbName, @PathVariable Set<String> tableNames);
}
