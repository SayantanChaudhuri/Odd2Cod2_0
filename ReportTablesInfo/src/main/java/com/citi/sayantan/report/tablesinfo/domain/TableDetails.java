package com.citi.sayantan.report.tablesinfo.domain;

import com.citi.sayantan.report.tablesinfo.converter.StringListConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TableDetails {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String dbName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DbType dbType;
    @Column(nullable = false)
    private String tableName;
    @Column(length = 500)
    @Convert(converter = StringListConverter.class)
    private List<String> maskedFields;
}
