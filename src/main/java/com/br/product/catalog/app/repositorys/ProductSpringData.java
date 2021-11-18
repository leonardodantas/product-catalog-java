package com.br.product.catalog.app.repositorys;

import com.br.product.catalog.app.models.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSpringData extends JpaRepository<Product, String> , JpaSpecificationExecutor<Product> {
}
