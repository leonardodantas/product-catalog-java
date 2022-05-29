package com.br.product.catalog.app.services;

import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.impl.FindProduct;
import com.br.product.catalog.app.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindProductServiceTest {

    @InjectMocks
    private FindProduct findProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testFindAllProducts() {

        final var products = new ArrayList<Product>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        when(productRepository.findAll())
                .thenReturn(products);

        final var allProducts = findProductService.findAllProducts();

        assertNotNull(allProducts);
        assertEquals(3, allProducts.size());
    }

    @Test
    public void testFindById() {
        final var id = "1d5asd8dad-asd54ad";

        final var product = Optional.of(new Product("1", "Notebook", "Dell", 1000));

        when(productRepository.findById(id))
                .thenReturn(product);

        final var productFind = findProductService.findById(id);

        assertNotNull(productFind);
        assertEquals(product.get().getPrice(), productFind.get().getPrice(), 000.1);

    }

    @Test
    public void testFindByIdWhereProductNotExist() {
        final var id = "1d5asd8dad-asd54ad";
        final var productEmpty = findProductService.findById(id);
        assertTrue(productEmpty.isEmpty());
    }
}
