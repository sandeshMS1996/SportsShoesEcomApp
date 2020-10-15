package com.sportshoes.ecom.security;

import com.sportshoes.ecom.entity.Customers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class ApplicationUserDetails implements UserDetails{

    private final Customers customers;

    public ApplicationUserDetails(Customers customers) {
        this.customers = customers;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(customers.getRole().name());
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customers.getRole().name());
        System.out.println(authority);
        return Collections.singletonList(authority);
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
        return true;
    }

    public Long getUserID() {
        return this.customers.getID();
    }
}
