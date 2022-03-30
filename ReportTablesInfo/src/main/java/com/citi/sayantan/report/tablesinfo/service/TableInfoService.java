package com.citi.sayantan.report.tablesinfo.service;

import com.citi.sayantan.report.tablesinfo.domain.MaskFieldsInfo;
import com.citi.sayantan.report.tablesinfo.domain.TableDetails;
import com.citi.sayantan.report.tablesinfo.repository.TableInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableInfoService {

    @Autowired
    private TableInfoRepository tableInfoRepository;

    public List<MaskFieldsInfo> getTableDetailsByDbName(String dbName, List<String> tableNames) {
        return this.tableInfoRepository.findTableNameAndMaskedFieldsByDbNameAndTableNameIn(dbName, tableNames);
    }

    public TableDetails upsertTableDetails(TableDetails tableDetails) {
        return this.tableInfoRepository.save(tableDetails);
    }
}
