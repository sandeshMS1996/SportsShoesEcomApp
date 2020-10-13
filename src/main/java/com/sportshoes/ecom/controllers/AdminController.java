package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.repos.CustomerRepo;
import com.sportshoes.ecom.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class AdminController {

    private final ProductRepo productRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    public AdminController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @GetMapping(value = "{id}")
    public Products getProductById(@PathVariable("id") Long id) {
        return this.productRepo.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product " + id + "not found"));

    }

    @PostMapping("add-new-product")
    public Products addProduct(@RequestBody Products product) {
        return this.productRepo.save(product);
    }


    @GetMapping("get-all-registered-users")
    public List<Customers> getRegisteredUsers() {
        return this.customerRepo.getAllRegisteredUsers();
    }

}
