package com.citi.sayantan.report.queryengine.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuditDetail {

    @Id
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDateTime createdTime;
    private String soeId;
    private String query;
}
