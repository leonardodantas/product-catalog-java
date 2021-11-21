package com.br.product.catalog.app.repositories;

import com.br.product.catalog.app.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSpringData extends JpaRepository<Product, String> , JpaSpecificationExecutor<Product> {
}
