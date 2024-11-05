package org.example.travelmicroservice.repositories;

import org.example.travelmicroservice.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

}
