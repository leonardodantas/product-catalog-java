package com.br.product.catalog.app.infra.database;

import com.br.product.catalog.app.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSpringData extends JpaRepository<Product, String> , JpaSpecificationExecutor<Product> {
}
