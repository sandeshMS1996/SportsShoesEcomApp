package com.sportshoes.ecom.services;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.entity.Purchase;
import com.sportshoes.ecom.repos.PurchaseRepo;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseService {

    @Autowired
    PurchaseRepo purchaseRepo;

    public Purchase savePurchase(long customerID, List<Long> productId) {
        List<Products> products = new ArrayList<>();
        for (Long aLong : productId) {
            products.add(new Products(aLong));
        }
        Purchase purchase = new Purchase();
        purchase.setCustomer(new Customers(customerID));
        purchase.setProduct(products);
        purchase.setQuantity(5);
        purchase.setTotalPrice(1000L);
        return purchaseRepo.save(purchase);
    }

    public List<Purchase> getAllMyPurchases(Long id) {
        return purchaseRepo.getPurchasesByCustomerId(id);
    }
}
