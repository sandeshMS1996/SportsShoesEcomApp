package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.JSONMappers.customerMapper;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import com.sportshoes.ecom.services.CustomerService;
import com.sportshoes.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@Validated
public class CustomerController {
    private final CustomerService customerService;
    private final ProductService productService;
    @Autowired
    public CustomerController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @PostMapping("register")
    public ResponseEntity<String> AddCustomer(@Valid @RequestBody customerMapper customer) {
        Customers customerEntity = this.customerService.addNewCustomer(customer.mapToCustomer());
        return ResponseEntity.ok("Registration success\n " + customerEntity);

    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changeMyPassword(@RequestBody String newPassword) {
        String password = this.customerService.changePassword(newPassword, 1L);
        return ResponseEntity.ok("Password changed: " + password);
    }

    @GetMapping(value = "product/{id}")
    public Products getProductById(@PathVariable("id") Long id) {
        Products product = productService.findProductById(id);
        if(product == null)
            throw new ProductNotFoundException("Product with " + id + " Not found" );
        return product;
    }

    @GetMapping("products")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("products/search-by-category/{id}")
    public List<Products> filterProductsByCategory(@PathVariable("id") long id) {
        return productService.getProductsByCategory(id);
    }


}
