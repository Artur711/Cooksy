package com.cooksy.util.converter;


import com.cooksy.dto.ProductDto;
import com.cooksy.dto.ShoppingListDto;
import com.cooksy.model.ShpList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ShoppingListToShoppingListDtoConverter {
    private final ProductToProductDtoConverter productToProductDtoConverter;

    public ShoppingListDto convert(ShpList shoppingList){
        List<ProductDto> products = productToProductDtoConverter.convertAll(shoppingList.getProducts());
        return new ShoppingListDto(shoppingList.getShpListId(),
                shoppingList.getIsConfirmed(),
                shoppingList.getUser(),
                shoppingList.getName(),
                shoppingList.getDate(),
                products);
    }

    public List<ShoppingListDto> convertAll(List<ShpList> shoppingLists) {
        return shoppingLists.stream().map(this::convert).collect(Collectors.toList());
    }

}
