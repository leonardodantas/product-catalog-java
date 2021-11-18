package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.controllers.impl.RemoveProductController;
import com.br.product.catalog.app.services.IRemoveProductService;
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
public class RemoveProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RemoveProductController removeProductController;

    @Mock
    private IRemoveProductService removeProductService;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(removeProductController).build();
    }

    @Test
    public void testRemoveProduct() throws Exception {
        String id = "123456";
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
