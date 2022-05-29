package com.br.product.catalog.app.infra.jsons.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record ProductRequestJson(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull @Positive
        double price
) {

}
