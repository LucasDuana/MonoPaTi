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
            tariffDTO.setStartDate(tariff.getStartDate().toString());
            return tariffDTO;
        }).collect(Collectors.toList());
    }

    public TariffDTO getTariffById(Long id) {
        return tariffRepository.findById(id).map(tariff -> {
            TariffDTO tariffDTO = new TariffDTO();
            tariffDTO.setPricePerKm(tariff.getCostPerKm());
            tariffDTO.setPricePerMinute(tariff.getCostPerMinute());
            tariffDTO.setStartDate(tariff.getStartDate().toString());
            return tariffDTO;
        }).orElse(null);
    }

    public TariffDTO createTariff(Tariff tariff) {
        return modelMapper.map(tariffRepository.save(tariff), TariffDTO.class);
    }

    public void deleteTariff(Long id) {
        tariffRepository.deleteById(id);
    }

}
