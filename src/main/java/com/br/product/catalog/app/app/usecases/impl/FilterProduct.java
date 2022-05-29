package com.br.product.catalog.app.app.usecases.impl;

import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.app.filters.ProductFilters;
import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.infra.database.specification.ProductSpecification;
import com.br.product.catalog.app.app.usecases.IFilterProduct;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FilterProduct implements IFilterProduct {

    private final IProductRepository productRepository;

    public FilterProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> execute(final ProductFilters productFilters) {
        return productRepository.findAll(ProductSpecification.filter(productFilters));
    }

}
