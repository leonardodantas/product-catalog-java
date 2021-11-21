package com.br.product.catalog.app.services.impl;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.services.IUpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UpdateProductService implements IUpdateProductService {

    private static final String ID_USER_NOT_FOUND = "ID user not found";

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productDTO, String id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ID_USER_NOT_FOUND));
        Product product = Product.of(id, productDTO);
        Product productUpdate = productRepository.save(product);
        return ProductResponseDTO.from(productUpdate);
    }
}
