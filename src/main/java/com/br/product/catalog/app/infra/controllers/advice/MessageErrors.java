package com.br.product.catalog.app.infra.controllers.advice;

import com.br.product.catalog.app.infra.jsons.response.DetailErrorResponseJson;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.Collection;
import java.util.List;

@Component
public class MessageErrors {

    private final MessageSource messageSource;

    public MessageErrors(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Collection<DetailErrorResponseJson> getErrors(final List<FieldError> fieldErrors){
        return fieldErrors.stream()
                .map(this::getError).toList();
    }

    private DetailErrorResponseJson getError(final FieldError field){
        final var message = messageSource.getMessage(field, LocaleContextHolder.getLocale());
        return DetailErrorResponseJson.of(field.getField(), message);
    }
}
