package org.example.travelmicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double kilomenters;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer scooterId;
    private Integer userId;

    private Integer stoppingStartStopId;
    private Integer stoppingEndStopId;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pause> pauses;


    public Travel() {
    }


}
