package com.br.product.catalog.app.services;

import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;

public interface IAddProductService {

    ProductResponseDTO addProduct(ProductRequestDTO productDTO);
}
