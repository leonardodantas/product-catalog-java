package com.br.product.catalog.app.app.repository;

import com.br.product.catalog.app.domain.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
    Collection<Product> findAll();
    void deleteById(String id);
    Collection<Product> findAll(Specification<Product> productSpecification);
}
