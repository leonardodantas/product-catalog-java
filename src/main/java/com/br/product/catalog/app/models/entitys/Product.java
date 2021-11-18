package com.br.product.catalog.app.models.entitys;

import com.br.product.catalog.app.models.request.ProductRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    private Product(ProductRequestDTO productDTO) {
        this.id = UUID.randomUUID().toString();
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
    }

    private Product(String id, ProductRequestDTO productDTO) {
        this.id = id;
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
    }

    public static Product from(ProductRequestDTO productDTO) {
        return new Product(productDTO);
    }

    public static Product of(String id, ProductRequestDTO productDTO) {
        return new Product(id, productDTO);
    }
}
