package org.example.apigateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/users").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                                .requestMatchers(
                                        "/v3/api-docs/",
                                        "/swagger-ui/",
                                        "/swagger-ui.html",
                                        "/webjars/**"
                                ).permitAll()
                                .requestMatchers("/admins/**").hasAuthority("admin")
                                .requestMatchers("/bills/**").hasAuthority("admin")
                                .requestMatchers("/admins/**").hasAuthority("admin")
                                .requestMatchers("/rates/**").hasAuthority("admin")
                                .requestMatchers("/accounts/{id}/enabled").hasAuthority("admin")
                                .requestMatchers("/accounts//{id}/disabled").hasAuthority("admin")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
