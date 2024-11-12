package org.example.travelmicroservice.dtos;


import jakarta.persistence.OneToMany;
import lombok.Data;
import org.example.travelmicroservice.model.Pause;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TravelDTO {


    private Double kilomenters;

    private Long scooterId;
    private Integer userId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Long stoppingStartStopId;
    private Long stoppingEndStopId;


    private int longPauses;

    public TravelDTO() {

    }

}
