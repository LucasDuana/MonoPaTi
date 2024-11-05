package org.example.scootermicroservice.service;

import org.example.scootermicroservice.dtos.ScooterDTO;
import org.example.scootermicroservice.dtos.StoppingDTO;
import org.example.scootermicroservice.model.Stopping;
import org.example.scootermicroservice.repositories.StoppingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoppingService {

    @Autowired
    private StoppingRepository stoppingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StoppingDTO> getAllStoppings() {
        List<Stopping> stoppings = stoppingRepository.findAll();
        return stoppings.stream()
                .map(stopping -> {
                    StoppingDTO stoppingDTO = modelMapper.map(stopping, StoppingDTO.class);
                    stoppingDTO.setScooters(
                            stopping.getScooters().stream()
                                    .map(scooter -> modelMapper.map(scooter, ScooterDTO.class))
                                    .collect(Collectors.toList())
                    );
                    return stoppingDTO;
                })
                .collect(Collectors.toList());
    }

    public StoppingDTO getStoppingById(Long id) {
        return this.stoppingRepository.findById(id)
                .map(stopping -> {
                    StoppingDTO stoppingDTO = modelMapper.map(stopping, StoppingDTO.class);
                    stoppingDTO.setScooters(
                            stopping.getScooters().stream()
                                    .map(scooter -> modelMapper.map(scooter, ScooterDTO.class))
                                    .collect(Collectors.toList())
                    );
                    return stoppingDTO;
                })
                .orElseThrow(() -> new IllegalArgumentException("No se encontro la parada con el id " + id));
    }

    public StoppingDTO createStopping(StoppingDTO stoppingDTO) {
        Stopping stopping = this.modelMapper.map(stoppingDTO, Stopping.class);
        return this.modelMapper.map(this.stoppingRepository.save(stopping), StoppingDTO.class);
    }

    public StoppingDTO updateStopping(Long id, StoppingDTO stoppingDTO) {
        Optional<Stopping> optionalStopping = this.stoppingRepository.findById(id);
        if (optionalStopping.isPresent()) {
            Stopping stopping = this.modelMapper.map(stoppingDTO, Stopping.class);
            return this.modelMapper.map(this.stoppingRepository.save(stopping), StoppingDTO.class);
        } else {
            throw new IllegalArgumentException("No se encontro la parada con el id " + id);
        }
    }

    public void deleteStoppingById(Long id) {
        if (!this.stoppingRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontro la parada con el id " + id);
        }
        this.stoppingRepository.deleteById(id);
    }
}
