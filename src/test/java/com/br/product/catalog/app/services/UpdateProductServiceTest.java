package com.br.product.catalog.app.services;


import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.impl.UpdateProduct;
import com.br.product.catalog.app.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductServiceTest {

    @InjectMocks
    private UpdateProduct updateProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testUpdateProduct() {
        final var id = "45454ddasd-dasdad54";
        final var productExist = new Product(id, "Notebook", "Dell", 5000);

        when(productRepository.findById(id))
                .thenReturn(Optional.of(productExist));

        final var productUpdate = new Product(id, "Notebook Dell", "Alta performace", 6000);

        when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(productUpdate);

        final var productSave = updateProductService.execute(id, productExist);

        assertEquals(productUpdate.getId(), productSave.getId());
        assertEquals(productUpdate.getDescription(), productSave.getDescription());
        assertEquals(productUpdate.getName(), productSave.getName());
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateProductWhereUserNotExist() {
        final var id = "45454ddasd-dasdad54";

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        updateProductService.execute(id, new Product());
    }

}
