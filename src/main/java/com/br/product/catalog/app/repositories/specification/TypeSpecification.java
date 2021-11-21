package com.br.product.catalog.app.repositories.specification;

public enum TypeSpecification {

    WHERE("WHERE"), AND("AND");

    private String type;

    TypeSpecification(String type){
        this.type = type;
    }
}
