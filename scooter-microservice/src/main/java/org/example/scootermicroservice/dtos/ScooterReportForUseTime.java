package org.example.scootermicroservice.dtos;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class ScooterReportForUseTime {

    private String scooterName;
    private LocalDate date;
    private Duration effectiveUsageTime;
}
