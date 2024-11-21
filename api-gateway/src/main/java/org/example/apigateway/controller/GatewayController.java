package org.example.apigateway.controller;

import org.example.apigateway.dtos.UserDTO;
import org.example.apigateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class GatewayController {

    @Autowired
    private AuthService gatewayService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) throws AuthenticationException {

        String token = gatewayService.authUser(user.getEmail(), user.getPassword());

        return ResponseEntity.ok(token);

    }

}
