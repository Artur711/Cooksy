package com.cooksy.util.converter;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductToProductDtoConverter {


    public ProductDto convert(Product product){
        return new ProductDto(product.getProductID(),
                product.getName(),
                product.getOriginal(),
                product.getAmount(),
                product.getUnit(),
                product.getMeasuresAmount(),
                product.getMeasuresUnitShort());
    }

    public List<ProductDto> convertAll(List<Product> products){
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
