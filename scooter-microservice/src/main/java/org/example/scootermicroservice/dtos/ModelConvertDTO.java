package org.example.scootermicroservice.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConvertDTO {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
