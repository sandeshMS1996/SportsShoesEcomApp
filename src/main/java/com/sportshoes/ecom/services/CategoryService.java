package com.sportshoes.ecom.services;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.repos.CategoryRepo;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {
    private CategoryRepo categoryRepo;
    private CurrentUserService userService;
    private ProductService productService;
    @Autowired
    public CategoryService(CategoryRepo categoryRepo, CurrentUserService userService, ProductService productService) {
        this.categoryRepo = categoryRepo;
        this.userService = userService;
        this.productService = productService;
    }

    public Category addNewCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category getCategoryById(long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return this.categoryRepo.findAll();
    }
    public void deleteCategory(Long id) {
        this.categoryRepo.deleteById(id);
    }

    @Transactional
    public void softDeleteCategory(Long id) {
        List<Products> productsByCategory = this.productService.getProductsByCategory(id);
        productsByCategory.forEach(a-> this.productService.deleteProduct(a.getID()));
        this.categoryRepo.softDeleteCategory(id);
    }
}
