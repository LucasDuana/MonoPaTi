package org.example.scootermicroservice.request;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class TravelRequest {

    private Long travelId;
    private Long scooterId;
    private LocalDate date;
    private Duration effectiveUsageTime;
}
