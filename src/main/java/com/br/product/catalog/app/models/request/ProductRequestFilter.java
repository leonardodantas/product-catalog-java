package com.br.product.catalog.app.models.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRequestFilter {

    private String nameOrDescription;
    private String minPrice;
    private String maxPrice;
}
