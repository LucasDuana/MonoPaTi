package org.example.travelmicroservice.controller;

import org.example.travelmicroservice.dtos.TravelDTO;
import org.example.travelmicroservice.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private TravelService travelService;

    //CRUD operations for travel

    //GET /travels get all travels
    @GetMapping("")
    public ResponseEntity<List<TravelDTO>> getTravels() {
        return ResponseEntity.ok(this.travelService.getTravels());
    }

    //POST /travels create a travel
    @PostMapping("")
    public ResponseEntity<TravelDTO> createTravel(TravelDTO travelDTO) {
        return ResponseEntity.ok(this.travelService.createTravel(travelDTO));
    }

    //GET /travels/{id} get a travel by id
    @GetMapping("/{id}")
    public ResponseEntity<TravelDTO> getTravelById(@PathVariable Long id){
        return ResponseEntity.ok(this.travelService.getTravelById(id));
    }

    //PUT /travels/{id} update a travel by id
    @PutMapping("/{id}")
    public ResponseEntity<TravelDTO> updateTravel(@PathVariable Long id, @RequestBody TravelDTO travelDTO) {
        return ResponseEntity.ok(this.travelService.updateTravel(id, travelDTO));
    }


    //DELETE /travels/{id} delete a travel by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTravelById(@PathVariable Long id) {
        this.travelService.deleteTravelById(id);
        return ResponseEntity.status(204).body("Travel with id " + id + " was deleted successfully");
    }





}
