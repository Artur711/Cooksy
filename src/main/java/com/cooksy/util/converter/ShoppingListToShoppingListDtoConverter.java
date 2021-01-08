package com.cooksy.util.converter;


import com.cooksy.dto.ProductDto;
import com.cooksy.dto.ShoppingListDto;
import com.cooksy.dto.UserDto;
import com.cooksy.model.ShoppingList;
import com.cooksy.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ShoppingListToShoppingListDtoConverter {
    private ProductToProductDtoConverter productToProductDtoConverter;

    public ShoppingListDto convert(ShoppingList shoppingList){
        List<ProductDto> products = productToProductDtoConverter.convertAll(shoppingList.getProduct());
        return new ShoppingListDto(shoppingList.getShoppingListId(),
                shoppingList.getIsConfirmed(),
                shoppingList.getUser().getUserId(),
                shoppingList.getName(),
                shoppingList.getDate(),
                products);
    }

    public List<ShoppingListDto> convertAll(List<ShoppingList> shoppingLists) {
        return shoppingLists.stream().map(this::convert).collect(Collectors.toList());
    }

}
