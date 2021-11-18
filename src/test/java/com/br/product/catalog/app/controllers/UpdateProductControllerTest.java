package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.controllers.impl.UpdateProductController;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.services.IUpdateProductService;
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
public class UpdateProductControllerTest {

    @InjectMocks
    private UpdateProductController updateProductController;

    @Mock
    private IUpdateProductService updateProductService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(updateProductController).build();
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductRequestDTO body = new ProductRequestDTO("Celular", "Dispositivo Movel", 5000);
        String id = "123456";
        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(body))
        ).andExpect(status().isOk());

    }

    @Test
    public void testValidatonBody() throws Exception {
        ProductRequestDTO body = new ProductRequestDTO();
        String id = "123456";
        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(body))
        ).andExpect(status().isBadRequest());

    }

}
