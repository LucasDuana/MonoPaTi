package org.example.scootermicroservice.dtos;

import lombok.Data;
import org.example.scootermicroservice.model.Scooter;

import java.util.List;

@Data
public class StoppingDTO {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;

    private List<ScooterDTO> scooters;

    public StoppingDTO() {
    }
}
