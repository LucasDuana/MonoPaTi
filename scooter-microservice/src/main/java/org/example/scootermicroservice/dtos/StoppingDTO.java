package org.example.scootermicroservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class StoppingDTO {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;

    @JsonIgnore
    private List<ScooterDTO> scooters;

    public StoppingDTO() {
    }
}
