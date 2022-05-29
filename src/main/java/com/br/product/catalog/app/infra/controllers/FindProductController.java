package com.br.product.catalog.app.infra.controllers;

import com.br.product.catalog.app.infra.jsons.response.ErrorResponseJson;
import com.br.product.catalog.app.infra.jsons.response.ProductResponseJson;
import com.br.product.catalog.app.app.usecases.IFindProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.Collection;

@Api(tags = "Products")
@RestController
public class FindProductController {

    private final IFindProduct findProductService;

    public FindProductController(final IFindProduct findProductService) {
        this.findProductService = findProductService;
    }

    @GetMapping("/products/{id}")
    @ApiOperation(value = "Find product by id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseJson.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseJson.class)
    })
    public ResponseEntity<?> execute(@PathVariable final String id) {
        final var response = findProductService.findById(id);
        return response
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products")
    @ApiOperation(value = "Find all product")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseJson.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseJson.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProductResponseJson> execute() {
        final var products = findProductService.findAllProducts();
        return products.stream().map(ProductResponseJson::from).toList();
    }
}
