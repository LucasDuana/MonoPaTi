package org.example.adminmicroservice.request;

import lombok.Data;

@Data
public class ScooterKmsRequest {

    private Long scooterId;
    private String scooterName;
    private Double totalDistance;
    private String totalUsageTime;

}
