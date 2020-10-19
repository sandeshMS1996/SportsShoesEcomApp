package com.sportshoes.ecom.controllers;

import com.sportshoes.ecom.AuthModels.AuthRequest;
import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.exceptions.ProductNotFoundException;
import com.sportshoes.ecom.security.JWTUtil;
import com.sportshoes.ecom.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("!test")
public class Authentication {

    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ProductNotFoundException("Incorect credentials");
        }
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmailId());
        final String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(token);
    }
}
