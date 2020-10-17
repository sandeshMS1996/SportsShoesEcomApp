package com.sportshoes.ecom.services;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        Products products = this.productRepo.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("product with ID " + id + " not found"));
       /* if(products.isDeletedFlag())
            return null;*/
        return products;
    }

    public List<Products> getAllProducts() {
        return this.productRepo.findAll();
    }

    public List<Products> getProductsByCategory(Long id) {
        return this.productRepo.findAllByCategoryId(new Category(id));/*.stream()
                .filter(a-> !a.isDeletedFlag()).collect(Collectors.toList());*/
    }

    public void deleteProduct(Long id) {
        System.out.println("deleting product + "+ id);
        /*Products product = this.productRepo.findById(id).orElseThrow(()->
                new ProductNotFoundException("product with ID " + id + " not found"));
        if(!product.isDeletedFlag()) {
            product.setDeletedFlag(true);
            this.productRepo.saveAndFlush(product);
        }*/
        this.productRepo.softDeleteProduct(id);
    }

    public int getProductPrice(Long id) {
        return this.productRepo.getProductPrice(id);
    }

    public boolean findProduct(Long id) {
        if(this.findProductById(id) == null)
            return false;
        return this.productRepo.existsById(id);
    }

}
