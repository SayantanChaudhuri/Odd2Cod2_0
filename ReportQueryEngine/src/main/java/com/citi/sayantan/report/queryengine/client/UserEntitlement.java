package com.citi.sayantan.report.queryengine.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "userEntitlementClient", url = "http://localhost:8003/api/v1")
public interface UserEntitlement {

    @GetMapping("/user/{soeId}/blockedtables")
    public List<String> getBlockedTables(@PathVariable String soeId);
}
