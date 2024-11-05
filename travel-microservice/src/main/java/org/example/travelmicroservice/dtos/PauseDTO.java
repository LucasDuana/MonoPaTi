package org.example.travelmicroservice.dtos;

import lombok.Data;
import org.example.travelmicroservice.model.Travel;

import java.time.LocalTime;

@Data
public class PauseDTO {

    private LocalTime startTime;
    private LocalTime endTime;

    private Travel travel;

    public PauseDTO(){
    }

}
