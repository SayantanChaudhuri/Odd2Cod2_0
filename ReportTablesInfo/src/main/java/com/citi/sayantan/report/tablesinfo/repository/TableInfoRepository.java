package com.citi.sayantan.report.tablesinfo.repository;

import com.citi.sayantan.report.tablesinfo.domain.MaskFieldsInfo;
import com.citi.sayantan.report.tablesinfo.domain.TableDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableInfoRepository extends JpaRepository<TableDetails, Long> {
    List<MaskFieldsInfo> findTableNameAndMaskedFieldsByDbNameAndTableNameIn(String dbName, List<String> tablesNames);
}
