package com.cooksy.util.converter.api;

import com.cooksy.dto.api.SpCuProductDto;
import com.cooksy.model.api.SpCuProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpCuProductToSpCuProductDtoConverter {

    public SpCuProductDto convert(SpCuProduct product) {
        return new SpCuProductDto(product.getId(),
                product.getName(),
                product.getOriginal(),
                product.getAmount(),
                product.getUnit(),
                product.getMeasures().getMetric().getAmount(),
                product.getMeasures().getMetric().getUnitShort());
    }

    public List<SpCuProductDto> convertAll(List<SpCuProduct> products) {
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
