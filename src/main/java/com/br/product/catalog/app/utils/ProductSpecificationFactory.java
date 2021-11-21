package com.br.product.catalog.app.utils;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.repositories.specification.Operations;
import com.br.product.catalog.app.repositories.specification.ProductSpecificationBuilder;
import com.br.product.catalog.app.repositories.specification.SQLName;
import com.br.product.catalog.app.repositories.specification.TypeSpecification;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationFactory {

    private ProductSpecificationFactory() {

    }

    public static Specification<Product> createSpecificationProduct(String descriptionOrName, String minPrice, String maxPrice) {
        return new ProductSpecificationBuilder()
                .with(SQLName.NAME, Operations.EQUALS, descriptionOrName, TypeSpecification.WHERE)
                .with(SQLName.DESCRIPTION, Operations.EQUALS, descriptionOrName, TypeSpecification.WHERE)
                .with(SQLName.PRICE, Operations.GREATERTHANOREQUALSTO, minPrice, TypeSpecification.AND)
                .with(SQLName.PRICE, Operations.LESSTHANOREQUALSTO, maxPrice, TypeSpecification.AND)
                .build();
    }
}
