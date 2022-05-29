package com.br.product.catalog.app.infra.controllers;

import com.br.product.catalog.app.infra.jsons.response.ErrorResponseJson;
import com.br.product.catalog.app.app.usecases.IRemoveProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

@Api(tags = "Products")
@RestController
public class RemoveProductController {

    private final IRemoveProduct removeProduct;

    public RemoveProductController(final IRemoveProduct removeProduct) {
        this.removeProduct = removeProduct;
    }

    @DeleteMapping("/products/{id}")
    @ApiOperation(value = "Remove product to catalog")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Error", response = ErrorResponseJson.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public void execute(@PathVariable final String id) {
        removeProduct.execute(id);
    }
}
