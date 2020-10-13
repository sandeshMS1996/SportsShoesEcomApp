package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Purchase;
import com.sportshoes.ecom.repos.CustomerRepo;
import com.sportshoes.ecom.repos.PurchaseRepo;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import com.sportshoes.ecom.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/customer/products")
public class ProductController {
    final PurchaseService purchaseService;

    @Autowired
    public ProductController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("{id}/add-to-cart")
    public ResponseEntity buyProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok("product " + id + "has been added to cart");
   }

   @PostMapping("buy-product")
   public ResponseEntity buyProduct() {
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Long userId = ((ApplicationUserDetails) principal).getUserID();
       purchaseService.savePurchase(userId, Arrays.asList(1L) );
       return ResponseEntity.ok("purchase success");
   }

   @GetMapping("/purchase-history")
    public List<Purchase> getMyPurchases() {
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Long userId = ((ApplicationUserDetails) principal).getUserID();
       return purchaseService.getAllMyPurchases(userId);
   }


}
