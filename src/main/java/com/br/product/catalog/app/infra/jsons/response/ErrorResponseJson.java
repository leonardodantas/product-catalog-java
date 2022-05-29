package com.br.product.catalog.app.infra.jsons.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;

public record ErrorResponseJson(
        @JsonProperty("status_code")
        int statusCode,
        String message
) {

    public static ErrorResponseJson from(final Collection<DetailErrorResponseJson> errors) {
        final var statusCode = HttpStatus.BAD_REQUEST.value();
        var message =
                errors.stream()
                        .reduce("", (partialResult, error) -> partialResult += error.getMessageError() + " e ", String::concat);

        message = message.substring(0, message.length() - 3);
        return new ErrorResponseJson(statusCode, message);
    }

    public static ErrorResponseJson of(final ErrorResponseMessageJson errorResponseStatus, final HttpStatus status) {
        return new ErrorResponseJson(status.value(), errorResponseStatus.message());
    }

    public static ErrorResponseJson of(final HttpStatus status, final String reason) {
        return new ErrorResponseJson(status.value(), reason);
    }
}