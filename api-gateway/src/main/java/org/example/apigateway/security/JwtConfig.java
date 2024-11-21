package org.example.apigateway.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class JwtConfig {

    private int jwtExpirationInMs;
    private String jwtSecret;

}