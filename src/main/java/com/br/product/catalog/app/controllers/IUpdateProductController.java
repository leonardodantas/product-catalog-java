package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.models.request.ProductRequestDTO;
import com.br.product.catalog.app.models.response.ErrorResponseDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

public interface IUpdateProductController {

    @PutMapping("/products/{id}")
    @ApiOperation(value = "Update product to catalog")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> updateProduct(@Valid @RequestBody ProductRequestDTO body, @PathVariable String id);
}
