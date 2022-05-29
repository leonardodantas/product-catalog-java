package com.br.product.catalog.app.app.usecases;

import com.br.product.catalog.app.domain.Product;

import java.util.Collection;
import java.util.Optional;

public interface IFindProduct {

    Collection<Product> findAllProducts();

    Optional<Product> findById(String id);
}
