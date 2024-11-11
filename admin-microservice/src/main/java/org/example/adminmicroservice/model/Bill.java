package org.example.adminmicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long tripId;
    private double totalCost;
    private LocalDate date;
    @ManyToOne
    private Tariff tariff;

}
