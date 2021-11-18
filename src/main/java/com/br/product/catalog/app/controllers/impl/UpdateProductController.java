package com.br.product.catalog.app.controllers.impl;

import com.br.product.catalog.app.controllers.IUpdateProductController;
import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.services.IUpdateProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Products")
@RestController
public class UpdateProductController implements IUpdateProductController {

    @Autowired
    private IUpdateProductService updateProductService;

    @Override
    public ResponseEntity<?> updateProduct(ProductRequestDTO body, String id) {
        ProductResponseDTO response = updateProductService.updateProduct(body, id);
        return ResponseEntity.ok(response);
    }
}
