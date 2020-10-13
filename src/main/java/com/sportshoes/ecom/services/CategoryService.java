package com.sportshoes.ecom.services;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.repos.CategoryRepo;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class CategoryService {
    private CategoryRepo categoryRepo;
    private CurrentUserService userService;
    @Autowired
    public CategoryService(CategoryRepo categoryRepo, CurrentUserService userService) {
        this.categoryRepo = categoryRepo;
        this.userService = userService;
    }

    public Category addNewCategory(Category category) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((ApplicationUserDetails) principal).getUserID();
        category.setAdmin(new Customers(userId));
        Category category1 = categoryRepo.save(category);
       category1.setAdmin(null);
       return category1;
    }


}
