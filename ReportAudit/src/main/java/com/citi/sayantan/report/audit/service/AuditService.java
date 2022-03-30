package com.citi.sayantan.report.audit.service;

import com.citi.sayantan.report.audit.domain.AuditDetail;
import com.citi.sayantan.report.audit.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public List<AuditDetail> getAuditDetailBySoeId(String soeId) {
        return this.auditRepository.findBySoeId(soeId);
    }

    public AuditDetail addAuditDetail(AuditDetail auditDetail) {
        return this.auditRepository.save(auditDetail);
    }
}
