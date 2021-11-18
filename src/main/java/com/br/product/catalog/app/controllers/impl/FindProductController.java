package com.br.product.catalog.app.controllers.impl;

import com.br.product.catalog.app.controllers.IFindProductController;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import com.br.product.catalog.app.services.IFindProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Products")
@RestController
public class FindProductController implements IFindProductController {

    @Autowired
    private IFindProductService findProductService;

    @Override
    public ResponseEntity<?> findProductById(String id) {
        ProductResponseDTO response = findProductService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> findAllProduct() {
        List<ProductResponseDTO> allProducts = findProductService.findAllProducts();
        return ResponseEntity.ok(allProducts);
    }
}
