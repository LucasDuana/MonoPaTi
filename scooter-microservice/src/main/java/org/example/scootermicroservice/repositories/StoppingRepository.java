package org.example.scootermicroservice.repositories;

import org.example.scootermicroservice.model.Stopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoppingRepository extends JpaRepository<Stopping, Long> {

    @Query("SELECT s FROM Stopping s JOIN s.scooters sc " +
            "WHERE (SELECT COUNT(sc) FROM Scooter sc WHERE sc.stopping = s AND sc.status = 'available') >= :minScooters")
    List<Stopping> findAllWithAvailableScooters(@Param("minScooters") int minScooters);


}
