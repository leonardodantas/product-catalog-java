package com.br.product.catalog.app.models.response;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ErrorResponseDTO {

    private final Integer status_code;
    private String message;

    private ErrorResponseDTO(List<DetailErrorResponseDTO> errors) {
        this.status_code = HttpStatus.BAD_REQUEST.value();
        this.message =
                errors.stream()
                        .reduce("", (partialResult, error) -> partialResult += error.getMessageError() + " e ", String::concat);

        message = message.substring(0, message.length() - 3);
    }

    private ErrorResponseDTO(HttpStatus status, ErrorResponseMessageDTO errorResponseStatus) {
        this.status_code = status.value();
        this.message = errorResponseStatus.getMessage();
    }

    private ErrorResponseDTO(HttpStatus status, String reason) {
        this.status_code = status.value();
        this.message = reason;
    }

    public static ErrorResponseDTO from(List<DetailErrorResponseDTO> errors) {
        return new ErrorResponseDTO(errors);
    }

    public static ErrorResponseDTO of(ErrorResponseMessageDTO errorResponseStatus, HttpStatus status) {
        return new ErrorResponseDTO(status, errorResponseStatus);
    }

    public static ErrorResponseDTO of(HttpStatus status, String reason) {
        return new ErrorResponseDTO(status, reason);
    }
}