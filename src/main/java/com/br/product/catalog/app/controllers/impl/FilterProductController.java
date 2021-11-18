package com.br.product.catalog.app.controllers.impl;

import com.br.product.catalog.app.controllers.IFilterProductController;
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
    public ResponseEntity<?> filterProducts(String descriptionOrName, String minPrice, String maxPrice) {
        List<ProductResponseDTO> response = filterProductService.filterProducts(descriptionOrName, minPrice, maxPrice);
        return ResponseEntity.ok(response);
    }
}
