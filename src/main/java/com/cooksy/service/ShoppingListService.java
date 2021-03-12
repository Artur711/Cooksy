package com.cooksy.service;

import com.cooksy.model.ShpList;
import com.cooksy.repository.ShoppingListRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@Data
@AllArgsConstructor
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShpList saveUsersShoppingList(ShpList shpList) {
        return shoppingListRepository.save(shpList);
    }
}
