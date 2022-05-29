package com.br.product.catalog.app.app.usecases.impl;

import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.IAddProduct;
import org.springframework.stereotype.Service;

@Service
public class AddProduct implements IAddProduct {

    private final IProductRepository productRepository;

    public AddProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(final Product product) {
        return productRepository.save(product);
    }
}
