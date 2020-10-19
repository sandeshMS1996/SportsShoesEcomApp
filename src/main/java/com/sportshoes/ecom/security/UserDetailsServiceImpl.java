package com.sportshoes.ecom.security;

import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
@Primary
@Profile("!test")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customers user = customerRepo.getUserByUserName(s);
        //Exception to be implemented and changed
        if(user == null) {
            System.out.println("Could not find user + " + s );
            throw new BadCredentialsException("Bad username : " + s);
        }
        return new ApplicationUserDetails(user);
    }
}
