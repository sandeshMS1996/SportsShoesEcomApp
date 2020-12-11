package com.sportshoes.ecom.repos;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
    List<Products> findAllByCategoryId(Category category);

    @Query("select p.price from Products p where p.ID = :id")
    int getProductPrice(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Products p set p.deletedFlag = true where p.ID = :id")
    void softDeleteProduct(@Param("id") Long id);


}
