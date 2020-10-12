package com.sportshoes.ecom.repos;

import com.sportshoes.ecom.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Long> {
    @Query("select c from Customers c where c.emailId = :email")
    Customers getUserByUserName(@Param("email") String email);
}
