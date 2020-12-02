package com.cooksy.service;


import com.cooksy.repository.ShoppingListRepository;

import com.cooksy.util.converter.ShoppingListDtoToShoppingListConverter;
import com.cooksy.util.converter.ShoppingListToShoppingListDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListDtoToShoppingListConverter shoppingListDtoToShoppingListConverter;
    private final ShoppingListToShoppingListDtoConverter shoppingListToShoppingListDtoConverter;



}
