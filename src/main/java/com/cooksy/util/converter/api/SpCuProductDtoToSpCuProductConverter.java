package com.cooksy.util.converter.api;

import com.cooksy.dto.api.SpCuProductDto;
import com.cooksy.model.api.Measures;
import com.cooksy.model.api.SpCuProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpCuProductDtoToSpCuProductConverter {

    public SpCuProduct convert(SpCuProductDto product) {
        return new SpCuProduct(product.getProductId(),
                product.getName(),
                product.getOriginal(),
                product.getAmount(),
                product.getUnit(),
                new Measures());
    }

    public List<SpCuProduct> convertAll(List<SpCuProductDto> products) {
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
