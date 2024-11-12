package org.example.adminmicroservice.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TariffDTO {

    private float pricePerMinute;
    private float pricePerKm;
    private double costPerExtensePause;
    private LocalDate startDate;


}
