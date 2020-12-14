package com.sportshoes.ecom.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/")
    public String run() {
        return "Welcome to Sports shoes Online portal";
    }
}
