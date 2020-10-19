package com.sportshoes.ecom.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportshoes.ecom.entity.Cart;
import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.entity.Purchase;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import com.sportshoes.ecom.services.ProductService;
import com.sportshoes.ecom.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("api/customer/products")
public class ProductController {
    private final PurchaseService purchaseService;
    private final ProductService productService;
    @Autowired
    public ProductController(PurchaseService purchaseService, ProductService productService) {
        this.purchaseService = purchaseService;
        this.productService =  productService;
    }

    @PostMapping("add-to-cart")
    public ResponseEntity<String> buyProduct(@RequestBody Cart newCart,
                                             HttpSession session) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if(cart==null)
            cart = new HashMap<>();
        Long price = (Long) session.getAttribute("price");
        if(price == null)
            price = 0L;
        if(!this.productService.findProduct(newCart.getProductId()))
            throw new ProductNotFoundException("Product " + newCart.getProductId() + " not found");
        price +=this.productService.getProductPrice(newCart.getProductId());
        cart.merge(newCart.getProductId(),newCart.getQuantity(),
                (a,b)->b+newCart.getQuantity());
        session.setAttribute("cart", cart);
        session.setAttribute("price", price);
        return ResponseEntity.ok("product ID" + newCart.getProductId() + " has been added to cart");
   }

   @GetMapping("view-cart")
   public ResponseEntity viewCart(HttpSession session) throws JsonProcessingException {
       Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
       System.out.println("cart  " + cart);
       if(cart == null)
           return ResponseEntity.ok("Cart is empty");
       Long price = (Long) session.getAttribute("price");
       String json = new ObjectMapper().writeValueAsString(cart);
       return ResponseEntity.ok(json + "\n\n" + "total Price " + price );
    }

   @PostMapping("buy-product")
   public ResponseEntity buyProduct(@AuthenticationPrincipal ApplicationUserDetails user, HttpSession session) {
       Map<Products, Integer> purchaseBag =new HashMap<> ();
       Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
       if(cart == null)
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cart is empty");
       for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
           purchaseBag.put(new Products(entry.getKey()),entry.getValue());
       }

       Purchase purchase = new Purchase();

       Long price = (Long) session.getAttribute("price");
        purchase.setTotalPrice(price);
       purchase.setCustomer(new Customers(user.getUserID()));
        purchase.setProduct(purchaseBag);
       System.out.println(purchase);
       this.purchaseService.savePurchase(purchase);
       session.setAttribute("cart", null);
       session.setAttribute("price", null);
       return ResponseEntity.ok("success");
   }

   @GetMapping("/purchase-history")
    public List<Purchase> getMyPurchases() {
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Long userId = ((ApplicationUserDetails) principal).getUserID();
       return purchaseService.getAllMyPurchases(userId);
   }


}
