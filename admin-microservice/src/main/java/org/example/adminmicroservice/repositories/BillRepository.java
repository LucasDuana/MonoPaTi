package org.example.adminmicroservice.repositories;

import org.example.adminmicroservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT SUM(b.totalCost) FROM Bill b WHERE YEAR(b.date) = :year AND MONTH(b.date) BETWEEN :startMonth AND :endMonth")
    Double getTotalAmountInYear(@Param("year") int year,@Param("startMonth") int startMonth,@Param("endMonth") int endMonth);



}
