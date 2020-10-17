package com.sportshoes.ecom.services;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.repos.CustomerRepo;
import com.sportshoes.ecom.security.ApplicationUserDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomerService  {

    private CustomerRepo repo;
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepo repo, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public Customers addNewCustomer(Customers customers) {
        System.out.println(customers);
        if (customers.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        customers.setPassword(passwordEncoder.encode(customers.getPassword()));
        return this.repo.saveAndFlush(customers);
    }
    @Transactional
    public String changePassword(String newPassword, Long userID) {
        /*ApplicationUserDetails principal = (ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userID = principal.getUserID();
        System.out.println("changing password for " + userID + " new Pass " + newPassword );
        */this.repo.changeMyPassword(this.passwordEncoder.encode(newPassword), userID);
        return newPassword;
    }

    public List<Customers> getAllRegisteredUsers() {
        return this.repo.getAllRegisteredUsers();
    }

    public void softDeleteAccount(Long id) {
        this.repo.softDeleteAccount(id);
    }



}
