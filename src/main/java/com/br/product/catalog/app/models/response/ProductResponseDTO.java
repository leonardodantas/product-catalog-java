package com.br.product.catalog.app.models.response;

import com.br.product.catalog.app.models.entities.Product;
import lombok.Getter;

@Getter
public class ProductResponseDTO {

    private final String id;
    private final String name;
    private final String description;
    private final double price;

    private ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public static ProductResponseDTO from(Product product) {
        return new ProductResponseDTO(product);
    }
}
