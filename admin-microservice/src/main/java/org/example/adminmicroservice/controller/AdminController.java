package org.example.adminmicroservice.controller;


import org.example.adminmicroservice.dtos.AdminDTO;
import org.example.adminmicroservice.model.Admin;
import org.example.adminmicroservice.service.AdminService;
import org.example.adminmicroservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BillService billService;


    @GetMapping("")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(this.adminService.getAllAdmins());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(Long id) {
        return ResponseEntity.ok(this.adminService.getAdminById(id));
    }


    @GetMapping("/scooter-status-summary")
    public ResponseEntity<Map<String, Integer>> getScooterStatusSummary() {
        Map<String, Integer> scooterStatusSummary = adminService.getScooterStatusSummary();
        return ResponseEntity.ok(scooterStatusSummary);
    }
    


    /*@GetMapping("/scooters-with-min-travels")
    public ResponseEntity<List<TravelsCountDTO>> getScootersWithMinTravelsInYear(
        @RequestParam int year,
        @RequestParam int minTravels) {
        List<TravelsCountDTO> scooters = adminService.getScootersWithMinTravelsInYear(year, minTravels);
        return ResponseEntity.ok(scooters);
    }
    */



    @GetMapping("/total-amount-in-year")
    public ResponseEntity<?> getTotalAmountInYear(@RequestParam("year") int year, @RequestParam("startMonth") int startMonth, @RequestParam("endMonth") int endMonth){
        return ResponseEntity.ok(this.adminService.getTotalAmountInYear(year, startMonth, endMonth));
    }


    @GetMapping("/scooter-usage-report")
    public ResponseEntity<?> getScooterUsageReport(@RequestParam("includePauses") boolean esta){
        return ResponseEntity.ok(this.adminService.getScooterUsageReport(esta));
    }


    @PostMapping("")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(this.adminService.createAdmin(admin));
    }


    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return ResponseEntity.ok(this.adminService.updateAdmin(id, admin));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        this.adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin with id " + id + " deleted successfully");
    }







}
