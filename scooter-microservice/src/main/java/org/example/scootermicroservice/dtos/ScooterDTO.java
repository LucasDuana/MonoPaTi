package org.example.scootermicroservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.example.scootermicroservice.model.Stopping;

@Data
public class ScooterDTO {

    private String name;
    private Long scooterId;
    private Double totalDistance;
    private String status;
    private Double latitude;
    private Double longitude;

    private StoppingDTO stopping;



    public ScooterDTO() {
    }
}
