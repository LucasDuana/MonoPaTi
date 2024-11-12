package org.example.adminmicroservice.controller;

import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    //CRUD operations for Bill entity
    //Get all the bills

    //CRUD operations for Bill entity
    //Get all the bills
    @GetMapping("")
    public ResponseEntity<List<BillDTO>> getAllBills() {
        return ResponseEntity.ok(this.billService.getAllBills());
    }

    /*//Create a bill
    @PostMapping("/create/{travelId}")
    public ResponseEntity<BillDTO> createBill(@PathVariable Long travelId) {
        return ResponseEntity.ok(this.billService.createBill(travelId));
    }*/

    //Create a bill for a travelID
    @PostMapping("/create/{travelId}")
    public ResponseEntity<BillDTO> createBill(@PathVariable Long travelId) {
        return ResponseEntity.ok(this.billService.createBill(travelId));
    }


}
