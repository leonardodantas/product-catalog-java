package com.br.product.catalog.app.controllers;

import com.br.product.catalog.app.models.response.ErrorResponseDTO;
import com.br.product.catalog.app.models.response.ProductResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.HttpURLConnection;

public interface IFilterProductController {

    @GetMapping("/products/search")
    @ApiOperation(value = "Filter products")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> filterProducts(@RequestParam(name = "q", required = false) String descriptionOrName, @RequestParam(name = "min_price", required = false) String minPrice, @RequestParam(name = "max_price", required = false) String maxPrice);
}
