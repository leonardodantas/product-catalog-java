package com.br.product.catalog.app.services;


import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.services.impl.UpdateProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductServiceTest {

    @InjectMocks
    private UpdateProductService updateProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testUpdateProduct() {
        ProductRequestDTO product = new ProductRequestDTO("Celular", "Dispositivo", 5000);
        String id = "45454ddasd-dasdad54";
        Product productSave = Product.from(product);

        when(productRepository.findById(id))
                .thenReturn(Optional.of(productSave));

        when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(productSave);

        ProductResponseDTO productResponseDTO = updateProductService.updateProduct(product, id);

        assertEquals(productSave.getId(), productResponseDTO.getId());
        assertEquals(productSave.getDescription(), productResponseDTO.getDescription());
        assertEquals(productSave.getName(), productResponseDTO.getName());
    }
    @Test(expected = ResponseStatusException.class)
    public void testUpdateProductWhereUserNotExist() {
        ProductRequestDTO product = new ProductRequestDTO("Celular", "Dispositivo", 5000);
        String id = "45454ddasd-dasdad54";

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        updateProductService.updateProduct(product,id);
    }

}
