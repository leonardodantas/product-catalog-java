package com.br.product.catalog.app.services;

import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.impl.RemoveProduct;
import com.br.product.catalog.app.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductServiceTest {

    @InjectMocks
    private RemoveProduct removeProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testRemoveProduct() {
        final var id = "1d5asd8dad-asd54ad";

        Optional<Product> product = Optional.of(new Product());

        when(productRepository.findById(id))
                .thenReturn(product);

        removeProductService.execute(id);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveProductWhereProductNotExist() {
        final var id = "1d5asd8dad-asd54ad";
        final Optional<Product> product = Optional.empty();

        when(productRepository.findById(id))
                .thenReturn(product);

        removeProductService.execute(id);

    }
}
