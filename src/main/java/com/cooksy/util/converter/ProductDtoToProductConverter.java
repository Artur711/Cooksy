package com.cooksy.util.converter;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.Grammage;
import com.cooksy.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoToProductConverter {

    public Product convert(ProductDto productDto){
        Grammage grammage = new Grammage();
        grammage.setGmId(productDto.getGmID());
        return new Product(productDto.getProductID(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getProductTypeID(),
                productDto.getMarketID(),
                grammage
                );
    }

    public List<Product> convertAll(List<ProductDto> productsDto){
        return  productsDto.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
