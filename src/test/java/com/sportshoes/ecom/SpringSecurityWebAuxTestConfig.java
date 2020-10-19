package com.sportshoes.ecom;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        SimpleGrantedAuthority role_admin = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_USER");
        User admin = new User("admin@gmail.com", "password", Arrays.asList(role_admin, role_user));
        User user = new User("user@gmail.com", "password", Arrays.asList(role_user));
        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }
}