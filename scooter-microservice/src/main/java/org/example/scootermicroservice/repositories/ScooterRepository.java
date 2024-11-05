package org.example.scootermicroservice.repositories;

import org.example.scootermicroservice.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {


    List<Scooter> findByStatus(String status);

    List<Scooter> findAllByOrderByTotalDistanceDesc();

}
