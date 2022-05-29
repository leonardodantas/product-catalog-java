package com.br.product.catalog.app.infra.controllers;

import com.br.product.catalog.app.infra.converters.ProductConverter;
import com.br.product.catalog.app.infra.jsons.request.ProductRequestJson;
import com.br.product.catalog.app.infra.jsons.response.ErrorResponseJson;
import com.br.product.catalog.app.infra.jsons.response.ProductResponseJson;
import com.br.product.catalog.app.app.usecases.IAddProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Products")
@RestController
public class AddProductController {

    private final IAddProduct addProduct;
    private final ProductConverter productConverter;

    public AddProductController(final IAddProduct addProduct, final ProductConverter productConverter) {
        this.addProduct = addProduct;
        this.productConverter = productConverter;
    }

    @PostMapping("/products")
    @ApiOperation(value = "Add product to catalog")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success", response = ProductResponseJson.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseJson.class)
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductResponseJson addProduct(@Valid @RequestBody final ProductRequestJson request) {
        final var response = addProduct.execute(productConverter.convert(request));
        return ProductResponseJson.from(response);
    }
}
