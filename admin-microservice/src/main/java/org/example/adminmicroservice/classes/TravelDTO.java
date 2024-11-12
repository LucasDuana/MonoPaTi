package org.example.adminmicroservice.classes;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TravelDTO {

    private Double kilomenters;

    private Long scooterId;
    private Long userId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer stoppingStartStopId;
    private Integer stoppingEndStopId;


    private int longPauses;

    public TravelDTO() {

    }
}
