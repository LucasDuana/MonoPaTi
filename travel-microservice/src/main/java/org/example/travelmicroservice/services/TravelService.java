package org.example.travelmicroservice.services;

import org.example.travelmicroservice.dtos.TravelDTO;
import org.example.travelmicroservice.model.Travel;
import org.example.travelmicroservice.repositories.TravelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TravelService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TravelRepository travelRepository;


    public List<TravelDTO> getTravels() {
        return travelRepository.findAll().stream().map(travel -> modelMapper.map(travel, TravelDTO.class)).collect(Collectors.toList());
    }

    public TravelDTO createTravel(TravelDTO travelDTO) {
        return modelMapper.map(travelRepository.save(modelMapper.map(travelDTO, Travel.class)), TravelDTO.class);
    }

    public TravelDTO getTravelById(Long id) {
        return modelMapper.map(travelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Travel not found")), TravelDTO.class);
    }

    public TravelDTO updateTravel(Long id, TravelDTO travelDTO) {
        Travel travel = travelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Travel not found"));
        travel.setKilomenters(travelDTO.getKilomenters());
        travel.setStoppingStartStopId(travelDTO.getStoppingStartStopId());
        travel.setStoppingEndStopId(travelDTO.getStoppingEndStopId());
        travel.setDate(travelDTO.getDate());
        travel.setStartTime(travelDTO.getStartTime());
        travel.setEndTime(travelDTO.getEndTime());
        travel.setScooterId(travelDTO.getScooterId());
        travel.setUserId(travelDTO.getUserId());
        return modelMapper.map(travelRepository.save(travel), TravelDTO.class);
    }

    public void deleteTravelById(Long id) {
        travelRepository.deleteById(id);
    }

}