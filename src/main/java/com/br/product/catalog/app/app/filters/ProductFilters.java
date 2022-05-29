package com.br.product.catalog.app.app.filters;

import lombok.Builder;

@Builder
public record ProductFilters(
        String nameOrDescription,
        String minPrice,
        String maxPrice
) {
}
