package org.example.scootermicroservice.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScooterReportForUseTime {

    private String scooterName;
    private String effectiveUsageTime;
    private Long ScooterId;
}
