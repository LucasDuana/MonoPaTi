package org.example.scootermicroservice.service;

import org.example.scootermicroservice.dtos.ScooterDTO;
import org.example.scootermicroservice.dtos.ScooterReportForUseTime;
import org.example.scootermicroservice.model.Scooter;
import org.example.scootermicroservice.model.Stopping;
import org.example.scootermicroservice.repositories.ScooterRepository;
import org.example.scootermicroservice.repositories.StoppingRepository;
import org.example.scootermicroservice.request.LocationUpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ScooterService {
    @Autowired
    private ScooterRepository scooterRepository;

    @Autowired
    private StoppingRepository stoppingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    public List<ScooterDTO> getScooters(String status) {
        if (status != null) {
            return this.scooterRepository.findByStatus(status).stream()
                    .map(scooter -> modelMapper.map(scooter, ScooterDTO.class))
                    .collect(Collectors.toList());
        }
        return this.scooterRepository.findAll().stream()
                .map(scooter -> modelMapper.map(scooter, ScooterDTO.class))
                .collect(Collectors.toList());
    }

    public List<ScooterDTO> getScootersOrderByDistance(){
        return this.scooterRepository.findAllByOrderByTotalDistanceDesc().stream()
                .map(scooter -> modelMapper.map(scooter, ScooterDTO.class))
                .collect(Collectors.toList());
    }

    public ScooterDTO registerScooterMaintenance(Long id) {
        Scooter scooter = this.scooterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el scooter con el id " + id));
        scooter.setStatus("maintenance");
        return this.modelMapper.map(this.scooterRepository.save(scooter), ScooterDTO.class);
    }

    public ScooterDTO getScooterById(Long id) {
        return this.modelMapper.map(this.scooterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el scooter con el id " + id)), ScooterDTO.class);
    }

    public ScooterDTO registerScooterAvailable(Long id) {
        Scooter scooter = this.scooterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el scooter con el id " + id));
        scooter.setStatus("available");
        return this.modelMapper.map(this.scooterRepository.save(scooter), ScooterDTO.class);
    }

    public ScooterDTO createScooter(ScooterDTO scooterDTO) {
        Scooter scooter = this.modelMapper.map(scooterDTO, Scooter.class);
        return this.modelMapper.map(this.scooterRepository.save(scooter), ScooterDTO.class);
    }

    public ScooterDTO registerScooterStopping(Long idScooter,Long idStopping){
        Scooter scooter = this.scooterRepository.findById(idScooter)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el scooter con el id " + idScooter));
        Stopping stopping= this.stoppingRepository.findById(idStopping)
                .orElseThrow(() -> new NoSuchElementException("No se encontro la parada con el id " + idStopping));

        if(scooter.getLongitude().equals(stopping.getLongitude()) && scooter.getLatitude().equals(stopping.getLatitude())){
            scooter.setLatitude(stopping.getLatitude());
            scooter.setLongitude(stopping.getLongitude());
            scooter.setStopping(stopping);
            return this.modelMapper.map(this.scooterRepository.save(scooter), ScooterDTO.class);
        }else{
            throw new IllegalArgumentException("La parada no coincide con la ubicacion del scooter");
        }

    }

    public ScooterDTO updateScooterLocation(Long id, LocationUpdateRequest location){
        Scooter scooter = this.scooterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el scooter con el id " + id));
        scooter.setLatitude(location.getLatitude());
        scooter.setLongitude(location.getLongitude());
        return this.modelMapper.map(this.scooterRepository.save(scooter), ScooterDTO.class);
    }

    public void deleteScooterById(Long id) {
        if (!this.scooterRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontro el scooter con el id " + id);
        }
        this.scooterRepository.deleteById(id);
    }

    public List<ScooterDTO> getScootersByStatus(String status) {
        return this.scooterRepository.findByStatus(status).stream()
                .map(scooter -> modelMapper.map(scooter, ScooterDTO.class))
                .collect(Collectors.toList());
    }

    public ScooterDTO updateScooter(Long id, ScooterDTO scooterDTO) {
        Scooter existingScooter = this.scooterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el scooter con el id " + id));
        existingScooter.setName(scooterDTO.getName());
        existingScooter.setStatus(scooterDTO.getStatus());

        return this.modelMapper.map(this.scooterRepository.save(existingScooter), ScooterDTO.class);
    }




    public ScooterDTO updateScooterUbication(Long id, double latitude, double longitude) {
        Scooter scooter = this.scooterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el scooter con el id " + id));
        scooter.getStopping().setLatitude(latitude);
        scooter.getStopping().setLongitude(longitude);
        return this.modelMapper.map(this.scooterRepository.save(scooter), ScooterDTO.class);
    }

    /**
     * public List<ScooterReportForUseTime> getScooterUsageReportWithNames() {
        String travelReportUrl = "http://travel-microservice/api/travels/usage-report";
        ScooterReportForUseTime[] travelReports = restTemplate.getForObject(travelReportUrl, ScooterReportForUseTime[].class);

        return Arrays.stream(travelReports).map(report -> {
            // Obtenemos el nombre del scooter basado en algún método de búsqueda, como por ID
            String scooterName = obtenerNombreScooterPorId(report.getScooterId());
            report.setScooterName(scooterName);
            return report;
        }).collect(Collectors.toList());
    }


    private String obtenerNombreScooterPorId(Integer scooterId) {

        return "Nombre del scooter";
    } */
}