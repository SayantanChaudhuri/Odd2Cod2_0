package com.citi.sayantan.report.userentitlement.repository;

import com.citi.sayantan.report.userentitlement.domain.QueryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryDetailsRepository extends JpaRepository<QueryDetails, String> {

    List<QueryDetails> findBySoeId(String soeId);
}
