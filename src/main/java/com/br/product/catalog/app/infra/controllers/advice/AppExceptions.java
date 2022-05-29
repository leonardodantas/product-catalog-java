package com.br.product.catalog.app.infra.controllers.advice;

import com.br.product.catalog.app.infra.jsons.response.ErrorResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptions {

    private final MessageErrors messageErrors;

    public AppExceptions(final MessageErrors messageErrors) {
        this.messageErrors = messageErrors;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handlerRuntimeException(final RuntimeException error) {
        final var response = ErrorResponseJson.of(HttpStatus.BAD_REQUEST, error.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException error) {
        final var fieldErrors = error.getBindingResult().getFieldErrors();
        final var listErrors = messageErrors.getErrors(fieldErrors);
        return new ResponseEntity<>(ErrorResponseJson.from(listErrors), HttpStatus.BAD_REQUEST);
    }
}
