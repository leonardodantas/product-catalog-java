package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.controllers.impl.FindProductController;
import com.br.product.catalog.app.services.IFindProductService;
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
public class FindProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FindProductController findProductController;

    @Mock
    private IFindProductService findProductService;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(findProductController).build();
    }

    @Test
    public void testFindProductById() throws Exception {
        String id = "123456";
        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
