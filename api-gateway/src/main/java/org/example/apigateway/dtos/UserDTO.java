package org.example.apigateway.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {


    private String userName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email es inválido")
    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String password;

    private String rol;

}
