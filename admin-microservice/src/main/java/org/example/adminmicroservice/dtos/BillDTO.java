package org.example.adminmicroservice.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.adminmicroservice.model.Tariff;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BillDTO {
    private Long userId;
    private Long tripId;
    private double totalCost;
    private LocalDate date;
    private Tariff tariff;

    public BillDTO(Long userId, Long tripId, double totalCost, LocalDate date, Tariff tariff) {
        this.userId = userId;
        this.tripId = tripId;
        this.totalCost = totalCost;
        this.date = date;
        this.tariff = tariff;
    }
}
