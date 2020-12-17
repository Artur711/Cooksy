package com.cooksy.util.converter;

import com.cooksy.dto.GrammageDto;
import com.cooksy.dto.ProductDto;
import com.cooksy.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductToProductDtoConverter {

    private GrammageToGrammageDtoConverter grammageToGrammageDtoConverter;

    public ProductDto convert(Product product){
        GrammageDto grammageDto = grammageToGrammageDtoConverter.convert(product.getGrammage());
        return new ProductDto(product.getProductID(),
                product.getName(),
                product.getPrice(),
                product.getProductTypeID(),
                product.getMarketID(),
                grammageDto);
    }

    public List<ProductDto> convertAll(List<Product> products){
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
