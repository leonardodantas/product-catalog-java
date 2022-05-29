package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.app.usecases.IFindProduct;
import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.infra.controllers.FindProductController;
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

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FindProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FindProductController findProductController;

    @Mock
    private IFindProduct findProductService;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(findProductController).build();
    }

    @Test
    public void testFindProductById() throws Exception {
        final var id = "123456";

        when(findProductService.findById(id))
                .thenReturn(Optional.of(new Product()));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testFindProductByIdNotFound() throws Exception {
        final var id = "123456";

        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
