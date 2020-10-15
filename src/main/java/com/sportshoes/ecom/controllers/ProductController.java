package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Cart;
import com.sportshoes.ecom.entity.Purchase;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import com.sportshoes.ecom.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    /*@PostMapping("add-to-cart")
    public ResponseEntity<String> buyProduct(@RequestBody Cart newCart,
                                             HttpSession session) {
        List<Cart> cart =(List<Cart>) session.getAttribute("cart");
        if(cart == null)
            cart =  new ArrayList<>();
        cart.add(newCart);
        return ResponseEntity.ok("product " + newCart.getProductId() + "has been added to cart");
   }

   @GetMapping("viewCart")
   public List<Cart> viewCart(HttpSession session) {
       List<Cart> cart =(List<Cart>) session.getAttribute("cart");
       return cart;
    }

*/   @PostMapping("buy-product")
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
