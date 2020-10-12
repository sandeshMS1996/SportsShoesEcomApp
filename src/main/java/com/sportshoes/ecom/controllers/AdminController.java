package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
public class AdminController {

    private final ProductRepo productRepo;

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
}
