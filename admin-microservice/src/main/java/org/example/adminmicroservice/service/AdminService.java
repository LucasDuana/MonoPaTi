package org.example.adminmicroservice.service;

import org.example.adminmicroservice.dtos.AdminDTO;
import org.example.adminmicroservice.dtos.ScooterMaintenanceDTO;
import org.example.adminmicroservice.model.Admin;
import org.example.adminmicroservice.repositories.AdminRepository;
import org.example.adminmicroservice.repositories.BillRepository;
import org.example.adminmicroservice.request.ScooterKmsRequest;
import org.example.adminmicroservice.request.TravelRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream().map(admin -> new AdminDTO(admin.getRol(), admin.getFirstName(),admin.getLastName())).collect(Collectors.toList());
    }

    public AdminDTO getAdminById(Long id) {
        return modelMapper.map(adminRepository.findById(id).get(), AdminDTO.class);
    }

    public AdminDTO createAdmin(Admin admin) {
        Admin savedAdmin = adminRepository.save(admin);
        return modelMapper.map(savedAdmin, AdminDTO.class);
    }


    public AdminDTO updateAdmin(Long id, Admin admin) {
        Admin adminToUpdate = adminRepository.findById(id).get();
        adminToUpdate.setFirstName(admin.getFirstName());
        adminToUpdate.setLastName(admin.getLastName());
        adminToUpdate.setRol(admin.getRol());
        Admin updatedAdmin = adminRepository.save(adminToUpdate);
        return modelMapper.map(updatedAdmin, AdminDTO.class);
    }


    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }


    public Map<String, Integer> getScooterStatusSummary() {

            String url="http://localhost:8080/scooters";


            ResponseEntity<List> inUseResponse = restTemplate.getForEntity(url + "?status=in_use", List.class);
            int scootersInUse = inUseResponse.getBody() != null ? inUseResponse.getBody().size() : 0;


            ResponseEntity<List> availableResponse = restTemplate.getForEntity(url + "?status=available", List.class);
            int scootersAvailable = availableResponse.getBody() != null ? availableResponse.getBody().size() : 0;


            ResponseEntity<List> inMaintenanceResponse = restTemplate.getForEntity(url + "?status=maintenance", List.class);
            int scootersInMaintenance = inMaintenanceResponse.getBody() != null ? inMaintenanceResponse.getBody().size() : 0;


            Map<String, Integer> scooterStatusSummary = new HashMap<>();
            scooterStatusSummary.put("operation", scootersInUse + scootersAvailable);
            scooterStatusSummary.put("maintenance", scootersInMaintenance);

            return scooterStatusSummary;
    }


    /*public List<TravelsCountDTO> getTravelsByYearAndMinTravels(int year, int minTravels) {
        //Consultar los scooters por año y por un mínimo de viajes
        List<Object[]> results = travelRepository.findScootersByYearAndMinTravels(year, minTravels);

        //Mapeo de resultados a DTOs
        return results.stream().map(result -> {
            Long scooterId = (Long) result[0];
            Long travelCount = (Long) result[1];

            TravelsCountDTO dto = new TravelsCountDTO();
            dto.setScooterId(scooterId);
            dto.setTravelsCount(travelCount);

            return dto;
        }).collect(Collectors.toList());
    }
    }*/

    public Double getTotalAmountInYear(int year, int startMonth,int endMonth) {
        return this.billRepository.getTotalAmountInYear(year, startMonth,endMonth);
    }


    public Double getTotalInvoiceAmount(String startDate, String endDate) {
        String url = "http://localhost:8080/invoices/total-amount?startDate=" + startDate + "&endDate=" + endDate;
        return restTemplate.getForObject(url, Double.class);
    }


    public List<ScooterMaintenanceDTO> getScooterUsageReport(boolean includePauses) {
        List<TravelRequest> travelRequests = fetchTravelData(includePauses);
        List<ScooterKmsRequest> scooters = fetchScooterData();

        if (travelRequests.isEmpty() || scooters.isEmpty()) return Collections.emptyList();

        Map<Long, ScooterKmsRequest> scooterMap = mapScootersById(scooters);

        return travelRequests.stream()
                .filter(travel -> travel.getScooterId() != null)
                .collect(Collectors.groupingBy(TravelRequest::getScooterId))
                .entrySet()
                .stream()
                .map(entry -> {
                    Long scooterId = entry.getKey();
                    List<TravelRequest> travels = entry.getValue();


                    ScooterKmsRequest scooter = scooterMap.get(scooterId);

                    Duration totalDuration = calculateTotalDuration(travels);
                    String totalDurationStr = formatDuration(totalDuration);

                    ScooterMaintenanceDTO reportDTO = new ScooterMaintenanceDTO();
                    reportDTO.setTotalEffectiveUsage(totalDurationStr);
                    reportDTO.setScooterName(scooter.getName());
                    reportDTO.setTotalKm(scooter.getTotalDistance());

                    return reportDTO;
                })
                .collect(Collectors.toList());
    }
















// Métodos privados auxiliares:


    private List<TravelRequest> fetchTravelData(boolean includePauses) {
        String travelReportUrl = includePauses
                ? "http://localhost:8082/travels/usage-report-pause"
                : "http://localhost:8082/travels/total-usage-report";

        TravelRequest[] travelRequests = restTemplate.getForObject(travelReportUrl, TravelRequest[].class);

        return travelRequests != null ? Arrays.asList(travelRequests) : Collections.emptyList();
    }

    private List<ScooterKmsRequest> fetchScooterData() {
        String scooterKmUrl = "http://localhost:8081/scooters/kilometers-report";

        ScooterKmsRequest[] scooters = restTemplate.getForObject(scooterKmUrl, ScooterKmsRequest[].class);

        return scooters != null ? Arrays.asList(scooters) : Collections.emptyList();
    }



    private Duration calculateTotalDuration(List<TravelRequest> travels) {
        return travels.stream()
                .map(TravelRequest::getEffectiveUsageTime)
                .map(this::parseFriendlyDuration)
                .filter(Objects::nonNull)
                .reduce(Duration.ZERO, Duration::plus);
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private Map<Long, ScooterKmsRequest> mapScootersById(List<ScooterKmsRequest> scooters) {
        return scooters.stream()
                .filter(scooter -> scooter.getScooterId() != null)
                .collect(Collectors.toMap(ScooterKmsRequest::getScooterId, scooter -> scooter));
    }

    private Duration parseFriendlyDuration(String durationStr) {
        if (durationStr == null || durationStr.isEmpty()) return Duration.ZERO;

        String[] parts = durationStr.split(" ");

        long hours = 0;
        long minutes = 0;

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("hour") || parts[i].contains("hrs")) {
                hours = Long.parseLong(parts[i - 1]);
            }
            if (parts[i].contains("minute") || parts[i].contains("min")) {
                minutes = Long.parseLong(parts[i - 1]);
            }
        }

        return Duration.ofHours(hours).plusMinutes(minutes);
    }


}
