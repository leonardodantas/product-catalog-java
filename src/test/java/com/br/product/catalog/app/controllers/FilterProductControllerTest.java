package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.controllers.impl.FilterProductController;
import com.br.product.catalog.app.services.IFilterProductService;
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
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FilterProductControllerTest {

    @InjectMocks
    private FilterProductController filterProductController;

    @Mock
    private IFilterProductService filterProductService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(filterProductController).build();
    }

    @Test
    public void testFilterProducts() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
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
