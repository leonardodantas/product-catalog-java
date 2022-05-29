package com.br.product.catalog.app.infra.database.specification;

public enum ColumnName {

    NAME("name"), DESCRIPTION("description"), PRICE("price");

    private final String field;

    ColumnName(final String field){
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
