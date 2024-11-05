package org.example.scootermicroservice.controller;

import org.example.scootermicroservice.dtos.ScooterDTO;
import org.example.scootermicroservice.request.LocationUpdateRequest;
import org.example.scootermicroservice.service.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {
    @Autowired
    private ScooterService scooterService;


    @GetMapping("")//works
    public ResponseEntity<List<ScooterDTO>> getAllScooters(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(scooterService.getScooters(status));
    }

    @GetMapping("/kilometers-report")
    public ResponseEntity<?> getScooterOrderByDistance(){
        return ResponseEntity.ok(scooterService.getScootersOrderByDistance());
    }

    @GetMapping("/{id}")//works
    public ResponseEntity<ScooterDTO> getScooterById(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.getScooterById(id));
    }

    //delete scooter by id
    @DeleteMapping("/{id}")//works
    public ResponseEntity<?> deleteScooterById(@PathVariable Long id) {
        scooterService.deleteScooterById(id);
        return ResponseEntity.status(204).body("El scooter con el id " + id + " fue sido borrado con exito");
    }

    //PUT register scooter in maintenance
    @PutMapping("/{id}/maintenance")
    public ResponseEntity<ScooterDTO> registerScooterMaintenance(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.registerScooterMaintenance(id));
    }

    //PATCH update scooter location
    @PatchMapping("/{id}/location")//works
    public ResponseEntity<ScooterDTO> updateScooterLocation(@PathVariable Long id, @RequestBody LocationUpdateRequest location){
        return ResponseEntity.ok(scooterService.updateScooterLocation(id, location));
    }

    //PATCH register scooter in stopping
    @PatchMapping("/{idScooter}/stoppings/{idStopping}")//works
    public ResponseEntity<ScooterDTO> registerScooterStopping(@PathVariable Long idScooter, @PathVariable Long idStopping) {
        return ResponseEntity.ok(scooterService.registerScooterStopping(idScooter, idStopping));
    }


    //PUT register scooter available
    public ResponseEntity<ScooterDTO> registerScooterAvailable(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.registerScooterAvailable(id));
    }

    //Create scooter
    @PostMapping("")
    public ResponseEntity<ScooterDTO> createScooter(@RequestBody ScooterDTO scooterDTO) {
        return ResponseEntity.ok(scooterService.createScooter(scooterDTO));
    }

    //Update scooter
    @PutMapping("/{id}")
    public ResponseEntity<ScooterDTO> updateScooter(@PathVariable Long id, @RequestBody ScooterDTO scooterDTO) {
        return ResponseEntity.ok(scooterService.updateScooter(id, scooterDTO));
    }

    @PatchMapping("/{id}/ubication")
    public ResponseEntity<ScooterDTO> updateScooterUbication(@PathVariable Long id, @RequestParam double latitude, @RequestParam double longitude) {
        return ResponseEntity.ok(scooterService.updateScooterUbication(id,latitude,longitude));
    }

}
