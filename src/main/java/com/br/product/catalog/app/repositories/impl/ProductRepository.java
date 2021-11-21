package com.br.product.catalog.app.repositories.impl;

import com.br.product.catalog.app.models.entities.Product;
import com.br.product.catalog.app.repositories.IProductRepository;
import com.br.product.catalog.app.repositories.ProductSpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    @Autowired
    private ProductSpringData productSpringData;

    @Override
    public Product save(Product product) {
        try {
            return productSpringData.save(product);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<Product> findById(String id) {
        return productSpringData.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productSpringData.findAll();
    }

    @Override
    public void deleteById(String id) {
        try {
            productSpringData.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Product> findAll(Specification<Product> productSpecification) {
        if(Objects.isNull(productSpecification)) {
            return productSpringData.findAll();
        }
        return productSpringData.findAll(productSpecification);
    }
}
