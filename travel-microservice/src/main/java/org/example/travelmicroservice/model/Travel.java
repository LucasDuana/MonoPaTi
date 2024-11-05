package org.example.travelmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer scooterId;
    private Integer userId;
    private Double kilomenters;

    private Integer stoppingStartStopId;
    private Integer stoppingEndStopId;



    public Travel() {

    }


}
