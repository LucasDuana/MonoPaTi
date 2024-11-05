package org.example.travelmicroservice.controller;

import jakarta.persistence.Id;
import org.example.travelmicroservice.dtos.PauseDTO;
import org.example.travelmicroservice.repositories.PauseRepository;
import org.example.travelmicroservice.services.PauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/pauses")
public class PauseController {

    @Autowired
    private PauseService pauseService;

    //CRUD
    //GET /pauses get all pauses
    @GetMapping("")
    public ResponseEntity<List<PauseDTO>> getPauses() {
        return ResponseEntity.ok(this.pauseService.getPauses());
    }

    //POST /pauses create a pause
    @PostMapping("")
    public ResponseEntity<PauseDTO> createPause(PauseDTO pauseDTO) {
        return ResponseEntity.ok(this.pauseService.createPause(pauseDTO));
    }

    //GET /pauses/{id} get a pause by id
    @GetMapping("/{id}")
    public ResponseEntity<PauseDTO> getPauseById(@PathVariable Long id) {
        return ResponseEntity.ok(this.pauseService.getPauseById(id));
    }
    //PUT /pauses/{id} update a pause by id
    @PutMapping("/{id}")
    public ResponseEntity<PauseDTO> updatePause(@PathVariable Long id, PauseDTO pauseDTO) {
        return ResponseEntity.ok(this.pauseService.updatePause(id, pauseDTO));
    }

    //DELETE /pauses/{id} delete a pause by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePauseById(@PathVariable Long id) {
        this.pauseService.deletePauseById(id);
        return ResponseEntity.ok().build();
    }


}
