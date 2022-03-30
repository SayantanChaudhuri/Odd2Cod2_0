package com.citi.sayantan.report.userentitlement.domain;

import com.citi.sayantan.report.userentitlement.converter.StringListConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    @Id
    private String soeId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String dbName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 500)
    @Convert(converter = StringListConverter.class)
    private List<String> blockedTables;

    @OneToMany
    @JoinColumn(name = "soeId")
    private List<QueryDetails> queries;
}
