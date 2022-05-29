package com.br.product.catalog.app.app.usecases;

import com.br.product.catalog.app.domain.Product;

public interface IAddProduct {

    Product execute(Product product);
}
