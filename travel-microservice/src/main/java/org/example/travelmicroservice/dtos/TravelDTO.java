package org.example.travelmicroservice.dtos;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TravelDTO {


    private Double kilomenters;

    private Integer scooterId;
    private Integer userId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer stoppingStartStopId;
    private Integer stoppingEndStopId;

    public TravelDTO() {

    }

}
