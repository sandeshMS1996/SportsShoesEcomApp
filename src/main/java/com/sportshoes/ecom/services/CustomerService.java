package com.sportshoes.ecom.services;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerService  {
    private CustomerRepo repo;
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public CustomerService(CustomerRepo repo, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }


    public Customers addNewCustomer(Customers customers) {
        if(customers.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        customers.setPassword(passwordEncoder.encode(customers.getPassword()));
        return this.repo.save(customers);
    }
}
