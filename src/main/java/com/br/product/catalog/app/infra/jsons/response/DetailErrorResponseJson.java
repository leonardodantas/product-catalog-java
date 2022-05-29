package com.br.product.catalog.app.infra.jsons.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record DetailErrorResponseJson(
        String field,
        String message
) {

    public static DetailErrorResponseJson of(final String field, final String message) {
        return new DetailErrorResponseJson(field, message);
    }

    public static DetailErrorResponseJson of(final String message) {
        return new DetailErrorResponseJson("", message);
    }

    public String getMessageError() {
        return field + " " + message;
    }

}