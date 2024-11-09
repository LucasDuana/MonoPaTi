package org.example.travelmicroservice.dtos;

import lombok.Data;

@Data
public class TravelsCountDTO {

    private Long scooterId;
    private Long travelsCount;

    public TravelsCountDTO() {
    }
}
