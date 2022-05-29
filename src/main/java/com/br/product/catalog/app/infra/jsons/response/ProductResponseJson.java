package com.br.product.catalog.app.infra.jsons.response;

import com.br.product.catalog.app.domain.Product;

public record ProductResponseJson(
        String id,
        String name,
        String description,
        double price
) {


    public static ProductResponseJson from(final Product product) {
        return new ProductResponseJson(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
