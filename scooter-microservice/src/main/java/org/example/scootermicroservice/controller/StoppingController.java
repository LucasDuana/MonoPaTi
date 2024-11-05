package org.example.scootermicroservice.controller;

import org.example.scootermicroservice.dtos.StoppingDTO;
import org.example.scootermicroservice.service.StoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stoppings")
public class StoppingController {
    @Autowired
    private StoppingService stoppingService;

    @GetMapping("")
    public ResponseEntity<List<StoppingDTO>> getStoppings() {
       return ResponseEntity.ok(stoppingService.getAllStoppings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStoppingById(@PathVariable Long id){
        StoppingDTO stopping = this.stoppingService.getStoppingById(id);
        return ResponseEntity.ok(stopping);

    }

    @PostMapping("")
    public ResponseEntity<?> createStopping(@RequestBody StoppingDTO stopping){
        return ResponseEntity.ok(this.stoppingService.createStopping(stopping));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStopping(@PathVariable Long id, @RequestBody StoppingDTO stopping){
        return ResponseEntity.ok(this.stoppingService.updateStopping(id, stopping));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStopping(@PathVariable Long id){
        this.stoppingService.deleteStoppingById(id);
        return ResponseEntity.status(204).body(" La Parada con el id " + id + " fue borrada con exito");
    }





}
