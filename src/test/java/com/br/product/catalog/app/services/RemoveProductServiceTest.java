package com.br.product.catalog.app.services;

import com.br.product.catalog.app.models.entitys.Product;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.repositorys.IProductRepository;
import com.br.product.catalog.app.services.impl.RemoveProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductServiceTest {

    @InjectMocks
    private RemoveProductService removeProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testRemoveProduct() {
        String id = "1d5asd8dad-asd54ad";
        ProductRequestDTO productResquestDTO = new ProductRequestDTO("teste", "teste", 1000);
        Optional<Product> product = Optional.of(Product.from(productResquestDTO)) ;

        when(productRepository.findById(id))
                .thenReturn(product);

        removeProductService.removeProduct(id);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);
    }

    @Test(expected = ResponseStatusException.class)
    public void testRemoveProductWhereProductNotExist() {
        String id = "1d5asd8dad-asd54ad";
        Optional<Product> product = Optional.empty();

        when(productRepository.findById(id))
                .thenReturn(product);

        removeProductService.removeProduct(id);

    }
}
