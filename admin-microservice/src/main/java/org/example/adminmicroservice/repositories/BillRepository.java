package org.example.adminmicroservice.repositories;

import org.example.adminmicroservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    //Get bills in range
    @Query("SELECT b FROM Bill b WHERE YEAR(b.date) = :year AND MONTH(b.date) >= :startMonth AND MONTH(b.date) <= :endMonth")
    public List<Bill> getBillsInRange(@Param("year")int year,@Param("startMonth") int startMonth,@Param("endMonth") int endMonth);

    @Query("SELECT SUM(b.totalCost) FROM Bill b WHERE YEAR(b.date) = :year AND MONTH(b.date) IN :months")
    Double getTotalAmountInYear(int year, List<Integer> months);


    @Query("SELECT SUM(b.totalCost) FROM Bill b WHERE YEAR(b.date) = :year AND MONTH(b.date) BETWEEN :startMonth AND :endMonth")
        Double getTotalBilledInRange(@Param("year") int year, @Param("startMonth") int startMonth, @Param("endMonth") int endMonth);

}
