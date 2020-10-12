package com.sportshoes.ecom.security;

import com.sportshoes.ecom.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;


public class ApplicationUserDetails implements UserDetails {

    private Customers customers;

    public ApplicationUserDetails(Customers customers) {
        this.customers = customers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customers.getRole().toString());
        System.out.println(authority.getAuthority());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return customers.getPassword();
    }

    @Override
    public String getUsername() {
        return customers.getEmailId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return customers.isActiveUser();
    }
}
