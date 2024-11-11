package org.example.adminmicroservice.classes;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDTO {
    private String name;
    private Double balance;
    private LocalDate creationDate;
}
