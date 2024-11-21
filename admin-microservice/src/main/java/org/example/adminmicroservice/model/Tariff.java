package org.example.adminmicroservice.model;



import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@NoArgsConstructor
@Data
@Document(collection = "pausas")
public class Tariff {

    @Id
    private Integer id;

    private float costPerMinute;
    private float costPerKm;
    private double costPerExtensePause;
    private LocalDate startDate;




}
