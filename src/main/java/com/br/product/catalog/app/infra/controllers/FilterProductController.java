package com.br.product.catalog.app.infra.controllers;

import com.br.product.catalog.app.app.filters.ProductFilters;
import com.br.product.catalog.app.app.usecases.IFilterProduct;
import com.br.product.catalog.app.infra.jsons.response.ErrorResponseJson;
import com.br.product.catalog.app.infra.jsons.response.ProductResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.Collection;


@Api(tags = "Products")
@RestController
public class FilterProductController {

    private final IFilterProduct filterProductService;

    public FilterProductController(final IFilterProduct filterProductService) {
        this.filterProductService = filterProductService;
    }

    @GetMapping("/products/search")
    @ApiOperation(value = "Filter products")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseJson.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseJson.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public Collection<ProductResponseJson> execute(@RequestParam(name = "q", required = false) final String descriptionOrName, @RequestParam(name = "min_price", required = false) final String minPrice, @RequestParam(name = "max_price", required = false) final String maxPrice) {
        final var filters = ProductFilters.builder().nameOrDescription(descriptionOrName).minPrice(minPrice).maxPrice(maxPrice)
                .build();
        final var response = filterProductService.execute(filters);
        return response.stream().map(ProductResponseJson::from).toList();
    }
}
