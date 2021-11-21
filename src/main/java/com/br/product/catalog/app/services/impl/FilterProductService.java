package com.br.product.catalog.app.services.impl;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.services.IFilterProductService;
import com.br.product.catalog.app.utils.ProductSpecificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterProductService implements IFilterProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> filterProducts(String descriptionOrName, String minPrice, String maxPrice) {
        Specification<Product> productSpecification = ProductSpecificationFactory.createSpecificationProduct(descriptionOrName, minPrice, maxPrice);
        List<Product> allProducts = productRepository.findAll(productSpecification);
        return allProducts.stream().map(ProductResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

}
