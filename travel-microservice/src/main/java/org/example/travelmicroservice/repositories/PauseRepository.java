package org.example.travelmicroservice.repositories;

import org.example.travelmicroservice.model.Pause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PauseRepository extends JpaRepository<Pause,Long> {


    @Query("SELECT p FROM Pause p WHERE p.travel.id = ?1")
    public List<Pause> findByTravelId(Long travelId);


    @Query("SELECT COUNT(p) FROM Pause p WHERE p.travel.id = :travelId AND " +
            "(EXTRACT(HOUR FROM p.endTime) * 60 + EXTRACT(MINUTE FROM p.endTime)) - " +
            "(EXTRACT(HOUR FROM p.startTime) * 60 + EXTRACT(MINUTE FROM p.startTime)) > 15")
    int countLongPausesByTravelId(@Param("travelId") Long travelId);

}
