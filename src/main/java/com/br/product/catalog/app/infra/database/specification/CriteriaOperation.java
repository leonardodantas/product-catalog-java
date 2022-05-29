package com.br.product.catalog.app.infra.database.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum CriteriaOperation {

    Equals(){
        @Override
        Predicate execute(final CriteriaBuilder criteriaBuilder, final Root root, final String column, final String value) {
            return criteriaBuilder.equal(
                    criteriaBuilder.upper(root.<String>get(ColumnName.NAME.getField())), value.toUpperCase());
        }
    }, GreatThanOrEqualTo(){
        @Override
        Predicate execute(final CriteriaBuilder criteriaBuilder, final Root root, final String column, final String value) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(ColumnName.PRICE.getField()), Double.parseDouble(value));
        }
    }, LessThanOrEqualTo(){
        @Override
        Predicate execute(final CriteriaBuilder criteriaBuilder, final Root root, final String column, final String value) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(ColumnName.PRICE.getField()), Double.parseDouble(value));
        }
    };


    abstract Predicate execute(CriteriaBuilder criteriaBuilder, Root root, String column, String valueToCompare);
}
