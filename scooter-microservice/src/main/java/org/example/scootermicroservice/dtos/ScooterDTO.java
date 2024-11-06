package org.example.scootermicroservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.example.scootermicroservice.model.Stopping;

@Data
public class ScooterDTO {

    private String name;
    private Double totalUsageTime;
    private Double totalDistance;
    private String status;


    public ScooterDTO() {
    }
}
