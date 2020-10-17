package com.sportshoes.ecom;

import com.sportshoes.ecom.entity.Category;
import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.entity.Products;
import com.sportshoes.ecom.services.CategoryService;
import com.sportshoes.ecom.services.CustomerService;
import com.sportshoes.ecom.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionListener;

import static com.sportshoes.ecom.entity.Customers.Role.ROLE_ADMIN;

@Component
@Slf4j
public class ApplicationRunner implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    CustomerService customerService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        seedData();
    }

    private void seedData() {
        Customers c = Customers.builder().emailId("admin@gmail.com").fname("admin").lname("admin")
                .age(21).address("myAddress").role(ROLE_ADMIN).isActiveUser(true).password("password").build();

        long customer = 0;
        try {
            customer = customerService.addNewCustomer(c).getID();
        }catch (Exception e) {
            log.info("could not load initial Data.. Exiting");
            return;
        }
        log.info("Loaded date " + c.getEmailId());
        long sports = categoryService.addNewCategory(new Category(customer, "sports")).getID();
        long students = categoryService.addNewCategory(new Category(customer, "students")).getID();
        long homeUtensils = categoryService.addNewCategory(new Category(customer, "home_utensils")).getID();
        Long cricketBat = productService.addNewProduct(new Products(customer, sports,"Cricket Bat", 1000)).getID();
        Long cricket_boll = productService.addNewProduct(new Products(customer, sports,"cricket_boll", 600)).getID();
        Long tennis_ball = productService.addNewProduct(new Products(customer, sports,"tennis_boll", 40)).getID();
        Long stove = productService.addNewProduct(new Products(customer, homeUtensils,"stove", 5000)).getID();
        Long vessel = productService.addNewProduct(new Products(customer, homeUtensils,"vessel", 500)).getID();
        Long Ref = productService.addNewProduct(new Products(customer, homeUtensils,"Refrigerators", 1200)).getID();
        Long test_book = productService.addNewProduct(new Products(customer, students,"pen_set_of_8", 100)).getID();
        Long pen_set_of_8 = productService.addNewProduct(new Products(customer, students,"vessel", 500)).getID();
        Long map = productService.addNewProduct(new Products(customer, students,"map", 149)).getID();



    }
}
