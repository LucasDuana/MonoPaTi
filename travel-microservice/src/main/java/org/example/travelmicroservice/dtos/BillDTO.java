package org.example.travelmicroservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BillDTO {
    private Long userId;
    private Long tripId;
    private double totalCost;
    private LocalDate date;



    public BillDTO(Long userId, Long tripId, double totalCost, LocalDate date) {
        this.userId = userId;
        this.tripId = tripId;
        this.totalCost = totalCost;
        this.date = date;

    }
}
