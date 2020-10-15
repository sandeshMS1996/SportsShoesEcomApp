package com.sportshoes.ecom.services;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Products addNewProduct(Products product) {
        return productRepo.save(product);
    }

    public Products findProductById(long id) {

        return this.productRepo.findById(id).orElse(null);
    }

    public List<Products> getAllProducts() {
        return this.productRepo.findAll();
    }

    public List<Products> getProductsByCategory(Long id) {
        return this.productRepo.findAllByCategoryId(new Category(id));
    }

    public void deleteProduct(Long id) {
        this.productRepo.deleteById(id);
    }

    public int getProductPrice(Long id) {
        return this.productRepo.getProductPrice(id);
    }

    public boolean findProduct(Long id) {
        return this.productRepo.existsById(id);
    }
}
