package org.example.travelmicroservice.services;

import org.example.travelmicroservice.dtos.PauseDTO;
import org.example.travelmicroservice.model.Pause;
import org.example.travelmicroservice.repositories.PauseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PauseService {

    @Autowired
    private PauseRepository pauseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PauseDTO> getPauses() {
        return pauseRepository.findAll().stream().map(pause -> modelMapper.map(pause, PauseDTO.class)).collect(Collectors.toList());
    }

    public PauseDTO createPause(PauseDTO pauseDTO) {
        return modelMapper.map(pauseRepository.save(modelMapper.map(pauseDTO, Pause.class)), PauseDTO.class);
    }

    public PauseDTO getPauseById(Long id) {
        return modelMapper.map(pauseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pause not found")), PauseDTO.class);
    }

    public PauseDTO updatePause(Long id, PauseDTO pauseDTO) {
        Pause pause = pauseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pause not found"));
        pause.setStartTime(pauseDTO.getStartTime());
        pause.setEndTime(pauseDTO.getEndTime());
        pause.setTravel(pauseDTO.getTravel());
        return modelMapper.map(pauseRepository.save(pause), PauseDTO.class);
    }

    public void deletePauseById(Long id) {
        pauseRepository.deleteById(id);
    }






}
