package com.br.product.catalog.app.app.usecases.impl;

import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.IFindProduct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FindProduct implements IFindProduct {

    private final IProductRepository productRepository;

    public FindProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> findAllProducts() {
         return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(final String id) {
        return productRepository.findById(id);
    }
}
