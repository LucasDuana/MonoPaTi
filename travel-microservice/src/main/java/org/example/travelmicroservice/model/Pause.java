package org.example.travelmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
public class Pause {

    @Id
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "travel_id", nullable = false)
    private Travel travel;
    @JsonIgnore
    public Pause() {
    }

}
