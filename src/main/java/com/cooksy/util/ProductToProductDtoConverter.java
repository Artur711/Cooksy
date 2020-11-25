package com.cooksy.util;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductToProductDtoConverter {

    public ProductDto convert(Product product){
        return new ProductDto(product.getProductID(),
                product.getName(),
                product.getPrice(),
                product.getProductTypeID(),
                product.getMarketID(),
                product.getGmID());
    }

    public List<ProductDto> convertAll(List<Product> products){
        return products.stream().map(this::convert).collect(Collectors.toList());
    }

}
