package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/categories")
public class CategoryController {
    CategoryRepo categoryRepo;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    @PostMapping("add-new-category")
    public Category addNewCategory(@RequestBody Category category) {
        return this.categoryRepo.save(category);
    }

    @GetMapping()
    @Secured("ADMIN")
    public List<Category> getAddCategories(@AuthenticationPrincipal User user) {

        return this.categoryRepo.findAll();
    }
    @GetMapping(value = "{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return this.categoryRepo.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Category not found"));
    }


}
