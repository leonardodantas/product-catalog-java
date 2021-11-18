package com.br.product.catalog.app.repositorys.specification;

import lombok.Getter;

@Getter
public class SearchProduct {

    private SQLName key;
    private Operations operation;
    private String value;
    private TypeSpecification typeSpecification;

    private SearchProduct(SQLName key, Operations operation, String value, TypeSpecification typeSpecification) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.typeSpecification = typeSpecification;
    }

    public static SearchProduct of(SQLName key, Operations operation, String value, TypeSpecification typeSpecification) {
        return new SearchProduct(key, operation, value, typeSpecification);
    }

    public String getKey() {
        return key.getField();
    }
}
