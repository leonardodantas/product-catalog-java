package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.app.usecases.IAddProduct;
import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.infra.controllers.AddProductController;
import com.br.product.catalog.app.infra.converters.ProductConverter;
import com.br.product.catalog.app.infra.jsons.request.ProductRequestJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AddProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AddProductController addProductController;

    @Mock
    private IAddProduct addProductService;

    @Mock
    private ProductConverter productConverter;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(addProductController).build();
    }

    @Test
    public void testAddProduct() throws Exception {

        final var request = new ProductRequestJson("Notebook", "Notebook Dell", 5000);

        when(addProductService.execute(ArgumentMatchers.any()))
                .thenReturn(new Product());

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request))
        ).andExpect(status().isCreated());

    }

    @Test
    public void testValidationRequest() throws Exception {

        final var request = new ProductRequestJson("", "", -10);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request))
        ).andExpect(status().isBadRequest());

    }

}
