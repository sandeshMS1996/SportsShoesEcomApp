package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("add-new-customer")
    public ResponseEntity<?> AddCustomer(@RequestBody Customers customer) {
        this.customerService.addNewCustomer(customer);
        return ResponseEntity.ok("new customer Added");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changeMyPassword(@RequestBody String newPassword) {
        String password = this.customerService.changePassword(newPassword);
        return ResponseEntity.ok("Password changed: " + password);
    }



}
