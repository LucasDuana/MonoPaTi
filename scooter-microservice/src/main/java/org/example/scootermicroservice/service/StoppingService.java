package org.example.scootermicroservice.service;

import org.example.scootermicroservice.dtos.ScooterDTO;
import org.example.scootermicroservice.dtos.StoppingDTO;
import org.example.scootermicroservice.model.Stopping;
import org.example.scootermicroservice.repositories.StoppingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    public StoppingDTO findStoppingsWithScooters(int minScooters,Double latitude,Double longitude){
        List<Stopping> stoppings=this.stoppingRepository.findAllWithAvailableScooters(minScooters);
        Stopping nearby=null;
        for(Stopping stopping:stoppings){
            if(nearby==null){
                nearby=stopping;
            }else{
                if(calculateDistance(latitude,longitude,stopping.getLatitude(),stopping.getLongitude())<calculateDistance(latitude,longitude,nearby.getLatitude(),nearby.getLongitude())){
                    nearby=stopping;
                }
            }
        }
        return this.modelMapper.map(nearby, StoppingDTO.class);
    }

    // Método para calcular la distancia usando la fórmula de Haversine
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radio de la Tierra en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}
