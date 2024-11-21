package org.example.adminmicroservice.service;

import org.example.adminmicroservice.dtos.TariffDTO;
import org.example.adminmicroservice.model.Tariff;
import org.example.adminmicroservice.repositories.TariffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TariffService {

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TariffDTO> getAllTariffs() {
        return tariffRepository.findAll().stream().map(tariff -> {
            TariffDTO tariffDTO = new TariffDTO();
            tariffDTO.setPricePerKm(tariff.getCostPerKm());
            tariffDTO.setPricePerMinute(tariff.getCostPerMinute());
            tariffDTO.setStartDate(tariff.getStartDate());
            return tariffDTO;
        }).collect(Collectors.toList());
    }

    public void saveTariff(TariffDTO tarrif){
        this.tariffRepository.save(modelMapper.map(tarrif, Tariff.class));
    }

}
