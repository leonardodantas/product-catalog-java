package com.br.product.catalog.app.infra.database.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public record OperationWithDetails(
        CriteriaOperation criteriaOperation, String field, String value
) {

    public static OperationWithDetails of(final CriteriaOperation criteriaOperation, final String field, final String value){
        return new OperationWithDetails(criteriaOperation, field, value);
    }

    Predicate executeCriteriaOperation(final CriteriaBuilder criteriaBuilder, final Root root){
        return criteriaOperation.execute(criteriaBuilder, root, field, value);
    }

}
