package com.citi.sayantan.report.audit.repository;

import com.citi.sayantan.report.audit.domain.AuditDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<AuditDetail, Long> {

    List<AuditDetail> findBySoeId(String soeId);
}
