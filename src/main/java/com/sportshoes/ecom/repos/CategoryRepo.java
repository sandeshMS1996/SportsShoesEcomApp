package com.sportshoes.ecom.repos;


import com.sportshoes.ecom.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query("update Category c set c.activeCategory = false where c.ID = :id")
    void softDeleteCategory(@Param("id") Long id);
}
