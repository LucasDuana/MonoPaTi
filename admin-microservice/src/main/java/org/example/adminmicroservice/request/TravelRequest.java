package org.example.adminmicroservice.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TravelRequest {

    private Long travelId;
    private Long scooterId;
    private String scooterName;
    private LocalDate date;
    private String effectiveUsageTime;


}
