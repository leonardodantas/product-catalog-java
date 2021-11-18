package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ErrorResponseDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.net.HttpURLConnection;

public interface IAddProductController {

    @PostMapping("/products")
    @ApiOperation(value = "Add product to catalog")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success", response = ProductResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO body);
}
