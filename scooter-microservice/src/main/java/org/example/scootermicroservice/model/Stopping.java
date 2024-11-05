package org.example.scootermicroservice.model;





import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stoppingId;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "stopping", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Scooter> scooters;


}
