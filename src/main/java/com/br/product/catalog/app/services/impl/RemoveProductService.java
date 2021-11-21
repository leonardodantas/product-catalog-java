package com.br.product.catalog.app.services.impl;

import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.services.IRemoveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RemoveProductService implements IRemoveProductService {

    private static final String ID_USER_NOT_FOUND = "ID user not found";

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void removeProduct(String id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ID_USER_NOT_FOUND));
        productRepository.deleteById(id);
    }
}
