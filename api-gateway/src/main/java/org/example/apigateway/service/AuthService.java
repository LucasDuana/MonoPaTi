package org.example.apigateway.service;

import org.example.apigateway.dtos.UserDTO;
import org.example.apigateway.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;

@Service
public class AuthService {



    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String authUser(String email, String password) throws AuthenticationException {

        System.out.println("Hola");

        UserDTO user = restTemplate.getForObject("http://localhost:8080/users?email="+email, UserDTO.class);
        System.out.println(user);

        System.out.println("Chau");

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Correo electrónico o contraseña incorrectos");
        }

        return jwtTokenProvider.generateToken(user);
    }


}