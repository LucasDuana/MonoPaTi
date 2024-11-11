package org.example.adminmicroservice.request;

import lombok.Data;

@Data
public class ScooterKmsRequest {

    private String name;
    private Long scooterId;
    private Double totalDistance;
    private String status;
    private Double latitude;
    private Double longitude;



}
