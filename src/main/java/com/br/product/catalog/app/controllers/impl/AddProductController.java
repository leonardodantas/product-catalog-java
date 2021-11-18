package com.br.product.catalog.app.controllers.impl;

import com.br.product.catalog.app.controllers.IAddProductController;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.services.IAddProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Products")
@RestController
public class AddProductController implements IAddProductController {

    @Autowired
    private IAddProductService addProductService;

    @Override
    public ResponseEntity<?> addProduct(ProductRequestDTO body) {
        ProductResponseDTO response = addProductService.addProduct(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
