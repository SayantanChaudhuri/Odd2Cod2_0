package com.citi.sayantan.report.queryengine.client;

import com.citi.sayantan.report.queryengine.domain.AuditDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auditClient", url = "http://localhost:8004/api/v1/audit")
public interface AuditClient {

    @PostMapping("/detail")
    AuditDetail sendAuditDetail(@RequestBody AuditDetail auditDetail);
}
