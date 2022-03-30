package com.citi.sayantan.report.audit.controller;

import com.citi.sayantan.report.audit.domain.AuditDetail;
import com.citi.sayantan.report.audit.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/{soeId}/details")
    public List<AuditDetail> getAuditDetailBySoeId(@PathVariable String soeId) {
        return this.auditService.getAuditDetailBySoeId(soeId);
    }

    @PostMapping("/detail")
    public AuditDetail addAuditDetail(@RequestBody AuditDetail auditDetail) {
        log.info("auditDetail :: {}", auditDetail);
        return this.auditService.addAuditDetail(auditDetail);
    }
}
