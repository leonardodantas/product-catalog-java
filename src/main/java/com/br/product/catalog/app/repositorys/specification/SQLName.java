package com.br.product.catalog.app.repositorys.specification;

public enum SQLName {

    NAME("name"), DESCRIPTION("description"), PRICE("price");

    private String field;

    SQLName(String field){
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
