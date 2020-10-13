package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.entity.Purchase;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import com.sportshoes.ecom.services.CategoryService;
import com.sportshoes.ecom.services.CustomerService;
import com.sportshoes.ecom.services.ProductService;
import com.sportshoes.ecom.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ProductService productService;
    private final CustomerService customerService;
    private CategoryService categoryService;
    private final PurchaseService purchaseService;
    @Autowired
    public AdminController(ProductService productService,
                           CustomerService customerservice,
                           CategoryService categoryService,
                           PurchaseService purchaseService) {
        this.productService = productService;
        this.customerService = customerservice;
        this.categoryService = categoryService;
        this.purchaseService = purchaseService;
    }

    @PostMapping("add-new-product")
    public Products addProduct(@RequestBody Products product) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userID = ((ApplicationUserDetails) principal).getUserID();
        product.setAdmin(new Customers(userID));
        return this.productService.addNewProduct(product);
    }

    @PostMapping("add-new-category")
    public Category addNewCategory(@RequestBody Category category) {
        return this.categoryService.addNewCategory(category);
    }


    @GetMapping("get-all-registered-users")
    public List<Customers> getRegisteredUsers() {
        return this.customerService.getAllRegisteredUsers();
    }


    @GetMapping(value = "category/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return this.categoryService.getCategoryById(id);
    }

    @GetMapping("category/get-all-categories")
    public List<Category> getAddCategories(@AuthenticationPrincipal User user) {
         return this.categoryService.getAllCategories();
    }

    @GetMapping("purchase-summery")
    public List<Purchase> getPurchaseSummary() {
        return this.purchaseService.getAllPurchases();
    }

    @PostMapping("category/delete-category/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category " + id + "deleted");
    }

    @PostMapping("product/delete-product/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok("Product " + id + "deleted");
    }
}
