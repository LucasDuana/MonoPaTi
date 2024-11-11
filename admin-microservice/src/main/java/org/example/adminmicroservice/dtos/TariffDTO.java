package org.example.adminmicroservice.dtos;

import lombok.Data;

@Data
public class TariffDTO {

    private float pricePerMinute;
    private float pricePerKm;
    private double costPerExtensePause;
    private String startDate;


}
