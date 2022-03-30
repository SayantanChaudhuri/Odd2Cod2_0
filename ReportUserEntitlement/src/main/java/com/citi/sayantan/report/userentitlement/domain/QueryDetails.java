package com.citi.sayantan.report.userentitlement.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QueryDetails {

    @Id
    @EqualsAndHashCode.Include
    private String queryName;
    @Column(nullable = false, length = 500)
    private String fullQuery;
    @Column(nullable = false, length = 8)
    private String soeId;
}
