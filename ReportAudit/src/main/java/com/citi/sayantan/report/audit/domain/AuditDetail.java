package com.citi.sayantan.report.audit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuditDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdTime;
    @NotNull
    @Column(nullable = false, length = 8)
    private String soeId;
    @NotNull
    @Column(nullable = false, length = 500)
    private String query;
}
