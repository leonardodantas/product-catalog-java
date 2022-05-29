package com.br.product.catalog.app.app.usecases.impl;

import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.app.usecases.IUpdateProduct;
import org.springframework.stereotype.Service;


@Service
public class UpdateProduct implements IUpdateProduct {

    private static final String ID_USER_NOT_FOUND = "ID user not found";

    private final IProductRepository productRepository;

    public UpdateProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(final String id, final Product product) {
        final var productToSave = productRepository.findById(id)
                .map(p -> product.ofId(id))
                .orElseThrow(() -> new RuntimeException(ID_USER_NOT_FOUND));

        return productRepository.save(productToSave);
    }
}
