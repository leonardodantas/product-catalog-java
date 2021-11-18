package com.br.product.catalog.app.repositorys.specification;

import com.br.product.catalog.app.models.entitys.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product> {

    private SearchProduct searchProduct;

    private ProductSpecification(SearchProduct searchProduct) {
        this.searchProduct = searchProduct;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchProduct.getOperation().equals(Operations.EQUALS)) {
            return  criteriaBuilder.like(
                    criteriaBuilder.upper(root.<String>get(searchProduct.getKey())), searchProduct.getValue().toUpperCase());
        } else if(searchProduct.getOperation().equals(Operations.GREATERTHANOREQUALSTO)) {
            return  criteriaBuilder.greaterThanOrEqualTo(root.<String>get(searchProduct.getKey()), searchProduct.getValue());
        } else if(searchProduct.getOperation().equals(Operations.LESSTHANOREQUALSTO)) {
            return  criteriaBuilder.lessThanOrEqualTo(root.<String>get(searchProduct.getKey()), searchProduct.getValue());
        }
        return null;
    }

    public static ProductSpecification from(SearchProduct searchProduct) {
        return new ProductSpecification(searchProduct);
    }
}
