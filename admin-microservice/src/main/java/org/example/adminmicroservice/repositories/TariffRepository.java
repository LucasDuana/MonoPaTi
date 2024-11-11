package org.example.adminmicroservice.repositories;

import org.example.adminmicroservice.dtos.TariffDTO;
import org.example.adminmicroservice.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    //get tariff

    @Query("SELECT t FROM Tariff t WHERE t.startDate <= :currentDate ORDER BY t.startDate DESC")
    Optional<Tariff> findLatestTariff(@Param("currentDate") LocalDate currentDate);

}
