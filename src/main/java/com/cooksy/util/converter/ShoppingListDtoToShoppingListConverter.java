package com.cooksy.util.converter;

import com.cooksy.dto.ProductDto;
import com.cooksy.dto.ShoppingListDto;
import com.cooksy.dto.UserDto;
import com.cooksy.model.Product;
import com.cooksy.model.ShoppingList;
import com.cooksy.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ShoppingListDtoToShoppingListConverter {
    private ProductDtoToProductConverter productDtoToProductConverter;
    public ShoppingList convert(ShoppingListDto shoppingListDto){
        User user = new User();
        user.setUserId(shoppingListDto.getUserId());
        List<Product> productList = productDtoToProductConverter.convertAll(shoppingListDto.getProducts());
        return new ShoppingList(
                shoppingListDto.getShoppingListId(),
                user,
                productList,
                shoppingListDto.getIsConfirmed(),
                shoppingListDto.getName(),
                shoppingListDto.getDate());
    }
    public List<ShoppingList> convertAll(List<ShoppingListDto> shoppingListDtos) {
        return shoppingListDtos.stream().map(this::convert).collect(Collectors.toList());
    }

}
