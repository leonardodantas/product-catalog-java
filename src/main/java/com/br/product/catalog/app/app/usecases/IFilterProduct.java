package com.br.product.catalog.app.app.usecases;

import com.br.product.catalog.app.domain.Product;
import com.br.product.catalog.app.app.filters.ProductFilters;

import java.util.Collection;

public interface IFilterProduct {

    Collection<Product> execute(ProductFilters productRequestFilter);
}
