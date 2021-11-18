package com.br.product.catalog.app.repositorys.specification;

import com.br.product.catalog.app.models.entitys.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductSpecificationBuilder {

    private final List<SearchProduct> params;

    public ProductSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public ProductSpecificationBuilder with(SQLName key, Operations operation, String value, TypeSpecification typeSpecification) {
        params.add(SearchProduct.of(key, operation, value, typeSpecification));
        return this;
    }

    public Specification<Product> build() {
        List<SearchProduct> paramsFilter = validateParams();

        if (paramsFilter.size() == 0) {
            return null;
        }

        List<Specification> specs = createListSpecification(paramsFilter);

        return getSpecification(paramsFilter, specs);
    }

    private Specification getSpecification(List<SearchProduct> paramsFilter, List<Specification> specs) {
        Specification result = Specification.where(specs.get(0));

        for (int i = 1; i < paramsFilter.size(); i++) {
            if(paramsFilter.get(i).getTypeSpecification().equals(TypeSpecification.WHERE)) {
                result = Specification.where(result).or(specs.get(i));
            } else {
                result = Specification.where(result).and(specs.get(i));
            }
        }
        return result;
    }

    private List<Specification> createListSpecification(List<SearchProduct> paramsFilter) {
        return paramsFilter.stream()
                .map(ProductSpecification::from)
                .collect(Collectors.toList());
    }

    private List<SearchProduct> validateParams() {
        return params.stream()
                .filter(param -> !Objects.isNull(param.getValue())).collect(Collectors.toList());
    }
}
