package com.sportshoes.ecom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportshoes.ecom.controllers.ProductController;
import com.sportshoes.ecom.services.ProductService;
import com.sportshoes.ecom.services.PurchaseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PurchaseService purchaseService;

    @Test
    public void testIt() {
        //Mockito.when();
    }*/
}
