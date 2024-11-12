package org.example.scootermicroservice.dtos;

import lombok.Data;


@Data
public class ScooterReportForUseTime {

    private String scooterName;
    private String effectiveUsageTime;
    private Long scooterId;
}
