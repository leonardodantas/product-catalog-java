package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.controllers.impl.AddProductController;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.services.IAddProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AddProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AddProductController addProductController;

    @Mock
    private IAddProductService addProductService;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(addProductController).build();
    }

    @Test
    public void testAddProduct() throws Exception {

        ProductRequestDTO body = new ProductRequestDTO("Celular", "Dispositivo Movel", 5000);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(body))
        ).andExpect(status().isCreated());

    }

    @Test
    public void testValidationBody() throws Exception {

        ProductRequestDTO body = new ProductRequestDTO();
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(body))
        ).andExpect(status().isBadRequest());

    }

}
