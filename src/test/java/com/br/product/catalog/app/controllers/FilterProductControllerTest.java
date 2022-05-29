package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.app.usecases.IFilterProduct;
import com.br.product.catalog.app.infra.controllers.FilterProductController;
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
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FilterProductControllerTest {

    @InjectMocks
    private FilterProductController filterProductController;

    @Mock
    private IFilterProduct filterProductService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(filterProductController).build();
    }

    @Test
    public void testFilterProducts() throws Exception {
        final var params = new LinkedMultiValueMap<String, String>();

        params.add("q", "descrição");
        params.add("min_prince", "100");
        params.add("max_price", "200");

        mockMvc.perform(MockMvcRequestBuilders.get("/products/search")
                        .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
