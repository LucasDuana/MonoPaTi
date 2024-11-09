package org.example.travelmicroservice.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TravelReportDTO {

    private Long travelId;
    private Long scooterId;
    private LocalDate date;
    private String effectiveUsageTime;
}
