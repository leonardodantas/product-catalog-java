package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.models.response.ErrorResponseDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

public interface IFindProductController {

    @GetMapping("/products/{id}")
    @ApiOperation(value = "Find product by id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> findProductById(@PathVariable String id);


    @GetMapping("/products")
    @ApiOperation(value = "Find all product")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> findAllProduct();
}
