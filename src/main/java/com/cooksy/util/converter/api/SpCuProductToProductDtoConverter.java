package com.cooksy.util.converter.api;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.api.SpCuProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpCuProductToProductDtoConverter {

    public ProductDto convert(SpCuProduct product) {
        return new ProductDto(product.getId(),
                product.getName(),
                product.getOriginal(),
                product.getAmount(),
                product.getUnit(),
                Double.valueOf(product.getMeasures().getMetric().getAmount()),
                product.getMeasures().getMetric().getUnitShort());
    }

    public List<ProductDto> convertAll(List<SpCuProduct> products) {
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
