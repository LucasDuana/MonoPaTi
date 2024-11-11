package org.example.scootermicroservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scooterId;

    @Column(unique = true)
    private String name;
    private String status;
    private Double totalUsageTime;
    private Double totalDistance;

    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "stopping_id", nullable = false)
    @JsonIgnore
    private Stopping stopping;



}
