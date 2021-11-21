package com.br.product.catalog.app.services;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.services.impl.FilterProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class FilterProductServiceTest {

    @InjectMocks
    private FilterProductService filterProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testFilterProducts() {
        String descriptionOrName = "Teste";
        String minPrice = "100";
        String maxPrice = "200";

        List<Product> products = createProducts();

        List<ProductResponseDTO> allProducts = filterProductService.filterProducts(descriptionOrName, minPrice, maxPrice);

        assertNotNull(allProducts);
    }

    private List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            ProductRequestDTO productRequestDTO = new ProductRequestDTO("Name " + i, "Description " + i, i * 2);
            products.add(Product.from(productRequestDTO));
        }
        return products;
    }
}
