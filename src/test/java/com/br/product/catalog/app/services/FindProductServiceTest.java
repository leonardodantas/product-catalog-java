package com.br.product.catalog.app.services;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.services.impl.FindProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindProductServiceTest {

    @InjectMocks
    private FindProductService findProductService;

    @Mock
    private IProductRepository productRepository;

    @Test
    public void testFindAllProducts() {

        List<Product> products = createProducts();
        when(productRepository.findAll())
                .thenReturn(products);

        List<ProductResponseDTO> allProducts = findProductService.findAllProducts();

        assertNotNull(allProducts);
        assertEquals(10, allProducts.size());
    }

    @Test
    public void testFindById() {
        String id = "1d5asd8dad-asd54ad";
        ProductRequestDTO productResquestDTO = new ProductRequestDTO("teste", "teste", 1000);
        Optional<Product> product = Optional.of(Product.from(productResquestDTO)) ;
        when(productRepository.findById(id))
                .thenReturn(product);

        ProductResponseDTO productResponseDTO = findProductService.findById(id);

        assertNotNull(productResponseDTO);
        assertEquals(productResquestDTO.getPrice(), productResponseDTO.getPrice(), 000.1);

    }

    @Test(expected = ResponseStatusException.class)
    public void testFindByIdWhereProductNotExist() {
        String id = "1d5asd8dad-asd54ad";
        Optional<Product> product = Optional.empty();
        when(productRepository.findById(id))
                .thenReturn(product);

        findProductService.findById(id);
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
