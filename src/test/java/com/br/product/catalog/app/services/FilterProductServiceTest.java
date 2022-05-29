package com.br.product.catalog.app.services;

import com.br.product.catalog.app.app.filters.ProductFilters;
import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.impl.FilterProduct;
import com.br.product.catalog.app.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterProductServiceTest {

    @InjectMocks
    private FilterProduct filterProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testFilterProducts() {
        final var descriptionOrName = "Notebooks";
        final var minPrice = "100";
        final var maxPrice = "200";

        final var products = new ArrayList<Product>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        when(productRepository.findAll(ArgumentMatchers.any()))
                .thenReturn(products);

        final var productsFilters = filterProductService.execute(ProductFilters.builder().nameOrDescription(descriptionOrName).minPrice(minPrice).maxPrice(maxPrice).build());

        assertNotNull(productsFilters);
        assertEquals(3, productsFilters.size());
    }

}
