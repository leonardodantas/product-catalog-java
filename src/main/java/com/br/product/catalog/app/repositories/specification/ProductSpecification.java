package com.br.product.catalog.app.repositories.specification;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.models.request.ProductRequestFilter;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;

public abstract class ProductSpecification {

    public static Specification<Product> filter(ProductRequestFilter productFilter) {
        return Specification.where(equalsName(productFilter.getNameOrDescription()))
                .or(equalsDescription(productFilter.getNameOrDescription()))
                .and(greaterThanOrEqualTo(productFilter.getMinPrice()))
                .and(lessThanOrEqualTo(productFilter.getMaxPrice()));
    }

    private static Specification<Product> equalsName(String name) {
        return (root, query, criteriaBuilder) -> {
            if(Strings.isNullOrEmpty(name)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.upper(root.<String>get(SQLName.NAME.getField())), name.toUpperCase());
        };
    }

    private static Specification<Product> equalsDescription(String description) {
        return (root, query, criteriaBuilder) -> {
            if(Strings.isNullOrEmpty(description)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.upper(root.<String>get(SQLName.DESCRIPTION.getField())), description.toUpperCase());
        };
    }

    private static Specification<Product> greaterThanOrEqualTo(String min) {
        return (root, query, criteriaBuilder) -> {
            if(Strings.isNullOrEmpty(min)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get(SQLName.PRICE.getField()), Double.parseDouble(min));
        };
    }

    private static Specification<Product> lessThanOrEqualTo(String max) {
        return (root, query, criteriaBuilder) -> {
            if(Strings.isNullOrEmpty(max)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(SQLName.PRICE.getField()), Double.parseDouble(max));
        };
    }
}
