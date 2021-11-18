package com.br.product.catalog.app.services;

import com.br.product.catalog.app.models.response.ProductResponseDTO;

import java.util.List;

public interface IFindProductService {

    List<ProductResponseDTO> findAllProducts();
    ProductResponseDTO findById(String id);
}
