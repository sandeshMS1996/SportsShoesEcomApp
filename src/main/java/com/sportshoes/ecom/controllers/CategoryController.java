package com.sportshoes.ecom.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.repos.CategoryRepo;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import com.sportshoes.ecom.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/categories")
public class CategoryController {
    CategoryRepo categoryRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    @PostMapping("add-new-category")
    public Category addNewCategory(@RequestBody Category category) {
            return this.categoryService.addNewCategory(category);
    }

    @GetMapping()
    @ResponseBody
    public List<Category> getAddCategories(@AuthenticationPrincipal User user) {

        List<Category> categories = this.categoryRepo.findAll();
        return categories;
    }
    @GetMapping(value = "{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((ApplicationUserDetails) principal).getUsername();
        Long userId=  ((ApplicationUserDetails) principal).getUserID();
        System.out.println("Current user " + username + " " + userId);
        return this.categoryRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("xx"));
    }


}
