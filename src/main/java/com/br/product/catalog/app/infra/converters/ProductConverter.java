package com.br.product.catalog.app.infra.converters;

import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.infra.jsons.request.ProductRequestJson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductConverter implements Converter<ProductRequestJson, Product> {

    @Override
    public Product convert(final ProductRequestJson json) {
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .description(json.description())
                .name(json.name())
                .price(json.price())
                .build();
    }
}
