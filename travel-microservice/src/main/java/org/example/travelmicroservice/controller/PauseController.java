package org.example.travelmicroservice.controller;

import org.example.travelmicroservice.dtos.PauseDTO;
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

    @GetMapping("")
    public ResponseEntity<List<PauseDTO>> getPauses() {
        return ResponseEntity.ok(this.pauseService.getPauses());
    }


    @PostMapping("")
    public ResponseEntity<PauseDTO> createPause(PauseDTO pauseDTO) {
        return ResponseEntity.ok(this.pauseService.createPause(pauseDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PauseDTO> getPauseById(@PathVariable Long id) {
        return ResponseEntity.ok(this.pauseService.getPauseById(id));
    }

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
