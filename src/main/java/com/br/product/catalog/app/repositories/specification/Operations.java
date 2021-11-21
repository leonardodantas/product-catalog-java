package com.br.product.catalog.app.repositories.specification;

public enum Operations {

    EQUALS(":"), GREATERTHANOREQUALSTO(">="), LESSTHANOREQUALSTO("<=");

    private String operation;

    Operations(String operation){
        this.operation = operation;
    }
}
