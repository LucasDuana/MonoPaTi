package org.example.travelmicroservice.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScooterDTO {
    private String name;
    private Long scooterId;
    private Double totalDistance;
    private String status;
    private Double latitude;
    private Double longitude;



    public ScooterDTO() {
    }

}
