package com.br.product.catalog.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    private Product(final String id, final Product product) {
        this.id = id;
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Product ofId(final String id) {
        return new Product(id, this);
    }

}
