package com.cooksy.util.converter;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.ShoppingProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ShoppingProductToProductDtoConverter {
    public ProductDto convert(ShoppingProduct shoppingProduct) {
        return new ProductDto(shoppingProduct.getId(),
                true,
                shoppingProduct.getProduct().getName(),
                shoppingProduct.getProduct().getOriginal(),
                shoppingProduct.getProduct().getAmount(),
                shoppingProduct.getProduct().getUnit(),
                shoppingProduct.getAmount(),
                shoppingProduct.getProduct().getMeasuresUnitShort(),
                shoppingProduct.getShpList().getShpListId(),
                shoppingProduct.getShpList().getDate());
    }

    public List<List<ProductDto>> convertAll(List<List<ShoppingProduct>> products) {
        return products.stream()
                .map(lst -> lst.stream().map(this::convert).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
