package com.sportshoes.ecom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportshoes.ecom.entity.Customers;
import com.sportshoes.ecom.repos.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
public class RegistrationTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepo customerRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails("admin@gmail.com")
    public void testRegistration() throws Exception {
        Customers customers = new Customers(1L);
        Mockito.when(this.customerRepo.save(customers)).thenReturn(customers);

        mockMvc.perform(post("/api/register").contentType("application/json")
        .content(this.objectMapper.writeValueAsString(customers))).andExpect(status().isBadRequest());


    }*/

}
