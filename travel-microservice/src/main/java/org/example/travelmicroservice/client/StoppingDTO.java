package org.example.travelmicroservice.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor

public class StoppingDTO {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;



    public StoppingDTO() {
    }


}
