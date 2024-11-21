package org.example.adminmicroservice.repositories;

import org.example.adminmicroservice.model.Tariff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TariffRepository extends MongoRepository<Tariff, Integer> {

    //get tariff
    @Query(value = "{ 'startDate': { $lt: ?0 } }", sort = "{ 'startDate': -1 }")
    Optional<Tariff> findLatestTariffBeforeDate(LocalDate date);


}
