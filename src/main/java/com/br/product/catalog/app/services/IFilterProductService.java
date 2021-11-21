package com.br.product.catalog.app.services;

import com.br.product.catalog.app.models.request.ProductRequestFilter;
import com.br.product.catalog.app.models.response.ProductResponseDTO;

import java.util.List;

public interface IFilterProductService {

    List<ProductResponseDTO> filterProducts(ProductRequestFilter productRequestFilter);
}
