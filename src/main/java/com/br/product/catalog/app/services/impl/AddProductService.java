package com.br.product.catalog.app.services.impl;

import com.br.product.catalog.app.models.entitys.Product;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.repositorys.IProductRepository;
import com.br.product.catalog.app.services.IAddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProductService implements IAddProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productDTO) {
        Product product = Product.from(productDTO);
        Product productSave = productRepository.save(product);
        return ProductResponseDTO.from(productSave);
    }
}
