package com.br.product.catalog.app.repositories;

import com.br.product.catalog.app.models.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
    List<Product> findAll();
    void deleteById(String id);
    List<Product> findAll(Specification<Product> productSpecification);
}
