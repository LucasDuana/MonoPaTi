package org.example.adminmicroservice.service;

import org.example.adminmicroservice.dtos.AdminDTO;
import org.example.adminmicroservice.model.Admin;
import org.example.adminmicroservice.repositories.AdminRepository;
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
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    //get all admins
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream().map(admin -> new AdminDTO(admin.getRol(), admin.getFirstName(),admin.getLastName())).collect(Collectors.toList());
    }

    //get admin by id
    public AdminDTO getAdminById(Long id) {
        return modelMapper.map(adminRepository.findById(id).get(), AdminDTO.class);
    }

    //create an admin
    public AdminDTO createAdmin(Admin admin) {
        Admin savedAdmin = adminRepository.save(admin);
        return modelMapper.map(savedAdmin, AdminDTO.class);
    }

    //update an admin
    public AdminDTO updateAdmin(Long id, Admin admin) {
        Admin adminToUpdate = adminRepository.findById(id).get();
        adminToUpdate.setFirstName(admin.getFirstName());
        adminToUpdate.setLastName(admin.getLastName());
        adminToUpdate.setRol(admin.getRol());
        Admin updatedAdmin = adminRepository.save(adminToUpdate);
        return modelMapper.map(updatedAdmin, AdminDTO.class);
    }

    //delete an admin
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    //return scooters in operation vs scooters in maintenance
    public Map<String, Integer> getScooterStatusSummary() {

            String url="http://localhost:8081/scooters";


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







    //get total invoice amount in date range
    public Double getTotalInvoiceAmount(String startDate, String endDate) {
        String url = "http://localhost:8083/invoices/total-amount?startDate=" + startDate + "&endDate=" + endDate;
        return restTemplate.getForObject(url, Double.class);
    }
















// Métodos privados auxiliares:



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

    private Duration parseFriendlyDuration(String durationStr) {
        if (durationStr == null || durationStr.isEmpty()) return null;
        String[] parts = durationStr.split(":");
        if (parts.length != 3) return null;

        try {
            long hours = Long.parseLong(parts[0]);
            long minutes = Long.parseLong(parts[1]);
            long seconds = Long.parseLong(parts[2]);
            return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
