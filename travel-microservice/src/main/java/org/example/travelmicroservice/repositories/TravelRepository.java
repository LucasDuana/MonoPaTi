package org.example.travelmicroservice.repositories;

import org.example.travelmicroservice.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {


    @Query("SELECT t.scooterId, COUNT(t) FROM Travel t WHERE FUNCTION('YEAR', t.date) = :year GROUP BY t.scooterId HAVING COUNT(t) >= :minTravels")
    List<Object[]> findScootersByYearAndMinTravels(int year, int minTravels);


}
