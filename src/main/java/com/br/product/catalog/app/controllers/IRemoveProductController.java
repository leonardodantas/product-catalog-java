package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.models.response.ErrorResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

public interface IRemoveProductController {

    @DeleteMapping("/products/{id}")
    @ApiOperation(value = "Remove product to catalog")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success"),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> removeProduct(@PathVariable String id);
}
