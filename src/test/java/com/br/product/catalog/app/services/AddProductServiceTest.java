package com.br.product.catalog.app.services;

import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.impl.AddProduct;
import com.br.product.catalog.app.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {

    @InjectMocks
    private AddProduct addProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testAddProduct() {
        final var productToSave = new Product("1", "Notebook", "Dell", 5000);
        when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(productToSave);

        final var product = addProductService.execute(productToSave);

        assertEquals(productToSave.getId(), product.getId());
        assertEquals(productToSave.getDescription(), product.getDescription());
        assertEquals(productToSave.getName(), product.getName());
    }
}
