package com.sportshoes.ecom.repos;

import com.sportshoes.ecom.entity.Purchase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    @Query("select p from Purchase p where p.customer.ID = :userId")
    List<Purchase> getPurchasesByCustomerId(@Param("userId") Long userId);

    @Query("select p from Purchase p where p.purchaseDate = :purchaseDate")
    List<Purchase> filterProductsByDate(@Param("purchaseDate") LocalDate purchaseDate);


}
