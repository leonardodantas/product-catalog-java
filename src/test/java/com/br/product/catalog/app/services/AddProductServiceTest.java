package com.br.product.catalog.app.services;

import com.br.product.catalog.app.models.entitys.Product;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositorys.IProductRepository;
import com.br.product.catalog.app.services.impl.AddProductService;
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
    private AddProductService addProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testAddProduct(){
        ProductRequestDTO product = new ProductRequestDTO("Celular", "Dispositivo", 5000);

        Product productSave = Product.from(product);
        when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(productSave);

        ProductResponseDTO productResponseDTO = addProductService.addProduct(product);

        assertEquals(productSave.getId(), productResponseDTO.getId());
        assertEquals(productSave.getDescription(), productResponseDTO.getDescription());
        assertEquals(productSave.getName(), productResponseDTO.getName());
    }
}
