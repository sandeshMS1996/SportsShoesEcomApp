package com.sportshoes.ecom.repos;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Long> {
    @Query("select c from Customers c where c.emailId = :email")
    Customers getUserByUserName(@Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Query("update Customers c set c.password = :newPassword where c.ID = :userId ")
    void changeMyPassword(@Param("newPassword") String newPassword, @Param("userId") Long userId);

    @Query("from Customers")
    List<Customers> getAllRegisteredUsers();

    @Modifying
    @Transactional
    @Query("update Customers c set c.isActiveUser = false where c.ID = :id")
    void softDeleteAccount(@Param("id") Long id);

}
