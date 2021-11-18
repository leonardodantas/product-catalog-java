package com.br.product.catalog.app.controllers.impl;

import com.br.product.catalog.app.controllers.IRemoveProductController;
import com.br.product.catalog.app.services.IRemoveProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Products")
@RestController
public class RemoveProductController implements IRemoveProductController {

    @Autowired
    private IRemoveProductService removeProductService;

    @Override
    public ResponseEntity<?> removeProduct(String id) {
        removeProductService.removeProduct(id);
        return ResponseEntity.ok().build();
    }
}
