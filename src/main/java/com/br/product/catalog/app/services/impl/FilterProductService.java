package com.br.product.catalog.app.services.impl;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.models.request.ProductRequestFilter;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.repositories.specification.ProductSpecification;
import com.br.product.catalog.app.services.IFilterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterProductService implements IFilterProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> filterProducts(ProductRequestFilter productRequestFilter) {
        List<Product> allProducts = productRepository.findAll(ProductSpecification.filter(productRequestFilter));
        return allProducts.stream().map(ProductResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

}
