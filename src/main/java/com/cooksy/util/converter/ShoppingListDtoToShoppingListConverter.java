package com.cooksy.util.converter;

import com.cooksy.dto.ShoppingListDto;
import com.cooksy.model.Product;
import com.cooksy.model.ShpList;
import com.cooksy.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ShoppingListDtoToShoppingListConverter {
    private final ProductDtoToProductConverter productDtoToProductConverter;

    public ShpList convert(ShoppingListDto shoppingListDto) {
        User user = new User();
        user.setUserId(shoppingListDto.getUserId());
        List<Product> productList = productDtoToProductConverter.convertAll(shoppingListDto.getProducts());
        return new ShpList(
                shoppingListDto.getShoppingListId(),
                productList,
                user,
                shoppingListDto.getIsConfirmed(),
                shoppingListDto.getName(),
                shoppingListDto.getDate());
    }

    public List<ShpList> convertAll(List<ShoppingListDto> shoppingListDtos) {
        return shoppingListDtos.stream().map(this::convert).collect(Collectors.toList());
    }

}
