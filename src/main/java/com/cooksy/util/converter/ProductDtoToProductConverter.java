package com.cooksy.util.converter;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoToProductConverter {

    public Product convert(ProductDto productDto){
        return new Product(productDto.getProductId(),
                productDto.getName(),
                productDto.getOriginal(),
                productDto.getAmount(),
                productDto.getUnit(),
                productDto.getMeasuresAmount(),
                productDto.getMeasuresUnitShort());
    }

    public List<Product> convertAll(List<ProductDto> productsDto){
        return  productsDto.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
