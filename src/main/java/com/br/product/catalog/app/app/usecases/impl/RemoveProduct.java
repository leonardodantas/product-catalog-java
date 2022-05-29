package com.br.product.catalog.app.app.usecases.impl;

import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.app.usecases.IRemoveProduct;
import org.springframework.stereotype.Service;


@Service
public class RemoveProduct implements IRemoveProduct {

    private static final String ID_USER_NOT_FOUND = "ID user not found";

    private final IProductRepository productRepository;

    public RemoveProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(final String id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ID_USER_NOT_FOUND));

        productRepository.deleteById(id);
    }
}
