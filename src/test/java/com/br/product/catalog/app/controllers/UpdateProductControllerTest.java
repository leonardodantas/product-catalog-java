package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.app.usecases.IUpdateProduct;
import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.infra.controllers.UpdateProductController;
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
public class UpdateProductControllerTest {

    @InjectMocks
    private UpdateProductController updateProductController;

    @Mock
    private IUpdateProduct updateProductService;

    @Mock
    private ProductConverter productConverter;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(updateProductController).build();
    }

    @Test
    public void testUpdateProduct() throws Exception {
        final var request = new ProductRequestJson("Notebook", "Notebook Dell", 5000);
        final var id = "123456";

        when(updateProductService.execute(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(new Product());

        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request))
        ).andExpect(status().isOk());

    }

    @Test
    public void testValidationBody() throws Exception {
        final var request = new ProductRequestJson("", "", -10);
        String id = "123456";
        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request))
        ).andExpect(status().isBadRequest());

    }

}
