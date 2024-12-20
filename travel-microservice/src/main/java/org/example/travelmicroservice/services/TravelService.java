package org.example.travelmicroservice.services;

import org.example.travelmicroservice.client.ScooterDTO;
import org.example.travelmicroservice.client.StoppingDTO;
import org.example.travelmicroservice.dtos.*;
import org.example.travelmicroservice.model.Travel;
import org.example.travelmicroservice.repositories.PauseRepository;
import org.example.travelmicroservice.repositories.TravelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TravelService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private PauseRepository pauseRepository;

    @Autowired
    private RestTemplate restTemplate;


    public List<TravelDTO> getTravels() {
        return travelRepository.findAll().stream().map(travel -> modelMapper.map(travel, TravelDTO.class)).collect(Collectors.toList());
    }

    public TravelDTO createTravel(TravelDTO travelDTO) {
        Travel travel = modelMapper.map(travelDTO, Travel.class);
        return modelMapper.map(travelRepository.save(travel), TravelDTO.class);
    }

    public TravelDTO finalizarViaje(Long travelId){
        TravelDTO travel = this.getTravelById(travelId);

        String urlScooter = "http://localhost:8081/scooters/" + travel.getScooterId();
        ScooterDTO scooter = restTemplate.getForObject(urlScooter, ScooterDTO.class);

        String urlStopping = "http://localhost:8081/stoppings/" + travel.getStoppingEndStopId();
        StoppingDTO stopping = restTemplate.getForObject(urlStopping, StoppingDTO.class);

        if(scooter.getLatitude().equals(stopping.getLatitude()) && scooter.getLongitude().equals(stopping.getLongitude())) {
            String urlPay= "http://localhost:8083/bills/create/" + travelId;
            restTemplate.postForObject(urlPay, null, String.class);
            return travel;
        }
        return null;
    }



    public List<TravelsCountDTO> getTravelsByYearAndMinTravels(int year, int minTravels) {
        List<Object[]> results = travelRepository.findScootersByYearAndMinTravels(year, minTravels);

        return results.stream().map(result -> {
            Long scooterId = (Long) result[0];
            Long travelCount = (Long) result[1];
            TravelsCountDTO dto = new TravelsCountDTO();
            dto.setScooterId(scooterId);
            dto.setTravelsCount(travelCount);
            return dto;
        }).collect(Collectors.toList());
    }

    public TravelDTO getTravelById(Long id) {
        TravelDTO travelDTO = modelMapper.map(travelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Travel not found")), TravelDTO.class);
        int longPauses= this.pauseRepository.countLongPausesByTravelId(id);
        travelDTO.setLongPauses(longPauses);
        return travelDTO;
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


    private TravelReportDTO generateReport(Travel travel, Duration duration){


        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        String friendlyDuration = String.format("%d hours %d minutes", hours, minutes);

        TravelReportDTO reportDTO = new TravelReportDTO();
        reportDTO.setTravelId(travel.getId());
        reportDTO.setScooterId(travel.getScooterId());
        reportDTO.setDate(travel.getDate());
        reportDTO.setEffectiveUsageTime(friendlyDuration);

        return reportDTO;
    }
    public List<TravelReportDTO> getUsageReport() {
        List<Travel> travels = this.travelRepository.findAll();

        return travels.stream().map(travel -> {
            Duration totalTravelTime = Duration.between(travel.getStartTime(), travel.getEndTime());
            Duration totalPauseTime = travel.getPauses().stream()
                    .map(pause -> Duration.between(pause.getStartTime(), pause.getEndTime()))
                    .reduce(Duration.ZERO, Duration::plus);

            Duration effectiveUsageTime = totalTravelTime.minus(totalPauseTime);

            return this.generateReport(travel, effectiveUsageTime);
        }).collect(Collectors.toList());
    }

    public List<TravelReportDTO> getTotalUsageReport() {
        List<Travel> travels = travelRepository.findAll();

        return travels.stream().map(travel -> {
            Duration totalTravelTime = Duration.between(travel.getStartTime(), travel.getEndTime());
            return this.generateReport(travel, totalTravelTime);
        }).collect(Collectors.toList());
    }


}