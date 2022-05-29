package com.br.product.catalog.app.infra.database;

import com.br.product.catalog.app.app.repository.IProductRepository;
import com.br.product.catalog.app.domain.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository implements IProductRepository {

    private final ProductSpringData productSpringData;

    public ProductRepository(final ProductSpringData productSpringData) {
        this.productSpringData = productSpringData;
    }

    @Override
    public Product save(final Product product) {
        try {
            return productSpringData.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findById(final String id) {
        return productSpringData.findById(id);
    }

    @Override
    public Collection<Product> findAll() {
        return productSpringData.findAll();
    }

    @Override
    public void deleteById(final String id) {
        try {
            productSpringData.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Collection<Product> findAll(final Specification<Product> productSpecification) {
        return Optional.ofNullable(productSpecification)
                .map(productSpringData::findAll)
                .orElse(productSpringData.findAll());
    }
}
