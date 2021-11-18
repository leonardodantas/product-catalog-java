package com.br.product.catalog.app.services.impl;

import com.br.product.catalog.app.models.entitys.Product;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositorys.IProductRepository;
import com.br.product.catalog.app.services.IFindProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindProductService implements IFindProductService {

    private static final String ID_USER_NOT_FOUND = "ID user not found";

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> findAllProducts() {
         List<Product> products = productRepository.findAll();
         return products.stream().map(ProductResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ProductResponseDTO findById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ID_USER_NOT_FOUND));
        return ProductResponseDTO.from(product);
    }
}
