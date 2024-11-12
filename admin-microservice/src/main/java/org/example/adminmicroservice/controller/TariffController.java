package org.example.adminmicroservice.controller;

import org.example.adminmicroservice.dtos.TariffDTO;
import org.example.adminmicroservice.model.Tariff;
import org.example.adminmicroservice.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rates")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    //CRUD operations for Tariff entity



    //Get all the tariffs
    @GetMapping("")
    public ResponseEntity<List<TariffDTO>> getAllTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariffs());
    }

    //Get tariff by id
    @GetMapping("/{id}")
    public ResponseEntity<TariffDTO> getTariffById(@PathVariable Long id) {
        return ResponseEntity.ok(tariffService.getTariffById(id));
    }

    //Create a tariff
    @PostMapping("")
    public ResponseEntity<TariffDTO> createTariff(@RequestBody Tariff tariff) {
        return ResponseEntity.ok(tariffService.createTariff(tariff));
    }

    //Delete a tariff
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTariff(@PathVariable Long id) {
        tariffService.deleteTariff(id);
        return ResponseEntity.ok().build();
    }


}
