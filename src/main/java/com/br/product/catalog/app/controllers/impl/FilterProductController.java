package com.br.product.catalog.app.controllers.impl;

import com.br.product.catalog.app.controllers.IFilterProductController;
import com.br.product.catalog.app.models.request.ProductRequestFilter;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.services.IFilterProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "Products")
@RestController
public class FilterProductController implements IFilterProductController {

    @Autowired
    private IFilterProductService filterProductService;

    @Override
    public ResponseEntity<?> filterProducts(String nameOrDescription, String minPrice, String maxPrice) {
        List<ProductResponseDTO> response = filterProductService
                .filterProducts(ProductRequestFilter.builder().nameOrDescription(nameOrDescription).minPrice(minPrice).maxPrice(maxPrice)
                        .build());
        return ResponseEntity.ok(response);
    }
}
