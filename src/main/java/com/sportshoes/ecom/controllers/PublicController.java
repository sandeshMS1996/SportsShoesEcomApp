package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.JSONMappers.customerMapper;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.services.CustomerService;
import com.sportshoes.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PublicController {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public PublicController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
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

    @PostMapping("register")
    public ResponseEntity<String> AddCustomer(@Valid @RequestBody customerMapper customer) {
        Customers customerEntity = this.customerService.addNewCustomer(customer.mapToCustomer());
        return ResponseEntity.ok("Registration success\n " + customerEntity);

    }
}
