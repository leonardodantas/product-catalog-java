package com.br.product.catalog.app.infra.database.specification;

import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.app.filters.ProductFilters;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public abstract class ProductSpecification {

    private ProductSpecification(){}

    public static Specification<Product> filter(final ProductFilters productFilter) {
        return Specification.where(equalsName(productFilter.nameOrDescription()))
                .or(equalsDescription(productFilter.nameOrDescription()))
                .and(greaterThanOrEqualTo(productFilter.minPrice()))
                .and(lessThanOrEqualTo(productFilter.maxPrice()));
    }

    private static Specification<Product> equalsName(final String name) {
        final var operationWithDetails = OperationWithDetails.of(CriteriaOperation.Equals, ColumnName.NAME.getField(), name);
        return (root, query, criteriaBuilder) -> getPredicate(operationWithDetails, criteriaBuilder, root);
    }

    private static Specification<Product> equalsDescription(final String description) {
        final var operationWithDetails = OperationWithDetails.of(CriteriaOperation.Equals, ColumnName.DESCRIPTION.getField(), description);
        return (root, query, criteriaBuilder) -> getPredicate(operationWithDetails, criteriaBuilder, root);
    }

    private static Specification<Product> greaterThanOrEqualTo(final String min) {
        final var operationWithDetails = OperationWithDetails.of(CriteriaOperation.GreatThanOrEqualTo, ColumnName.PRICE.getField(), min);
        return (root, query, criteriaBuilder) -> getPredicate(operationWithDetails, criteriaBuilder, root);
    }

    private static Specification<Product> lessThanOrEqualTo(final String max) {
        final var operationWithDetails = OperationWithDetails.of(CriteriaOperation.LessThanOrEqualTo, ColumnName.PRICE.getField(), max);
        return (root, query, criteriaBuilder) -> getPredicate(operationWithDetails, criteriaBuilder, root);
    }

    private static Predicate getPredicate(final OperationWithDetails operationWithDetails, final CriteriaBuilder criteriaBuilder, final Root root) {
        return Optional.ofNullable(operationWithDetails.value())
                .map(p -> operationWithDetails.executeCriteriaOperation(criteriaBuilder, root))
                .orElse(criteriaBuilder.conjunction());
    }
}
