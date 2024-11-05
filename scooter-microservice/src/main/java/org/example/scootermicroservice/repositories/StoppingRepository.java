package org.example.scootermicroservice.repositories;

import org.example.scootermicroservice.model.Stopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoppingRepository extends JpaRepository<Stopping, Long> {

}
