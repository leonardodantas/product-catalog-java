package com.br.product.catalog.app.infra.controllers;

import com.br.product.catalog.app.infra.converters.ProductConverter;
import com.br.product.catalog.app.infra.jsons.request.ProductRequestJson;
import com.br.product.catalog.app.infra.jsons.response.ErrorResponseJson;
import com.br.product.catalog.app.infra.jsons.response.ProductResponseJson;
import com.br.product.catalog.app.app.usecases.IUpdateProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Products")
@RestController
public class UpdateProductController  {

    private final IUpdateProduct updateProduct;
    private final ProductConverter productConverter;

    public UpdateProductController(final IUpdateProduct updateProduct, final ProductConverter productConverter) {
        this.updateProduct = updateProduct;
        this.productConverter = productConverter;
    }

    @PutMapping("/products/{id}")
    @ApiOperation(value = "Update product to catalog")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseJson.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseJson.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseJson execute(@PathVariable final String id, @Valid @RequestBody final ProductRequestJson request) {
        final var response = updateProduct.execute(id, productConverter.convert(request));
        return ProductResponseJson.from(response);
    }
}
