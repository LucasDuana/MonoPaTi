package org.example.adminmicroservice.dtos;

import lombok.Data;

@Data
public class ScooterMaintenanceDTO {

    private String scooterName;
    private String totalEffectiveUsage;
    private Double totalKm;


}
