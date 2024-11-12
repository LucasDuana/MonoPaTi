package org.example.adminmicroservice.dtos;

import lombok.Data;

@Data
public class AdminDTO {

    private String name;
    private String lastName;
    private String rol;

    public AdminDTO(String name, String lastName, String rol) {
        this.name = name;
        this.lastName = lastName;
        this.rol = rol;
    }
}
