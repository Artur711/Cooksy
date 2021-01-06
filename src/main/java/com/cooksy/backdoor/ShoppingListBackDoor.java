package com.cooksy.backdoor;


import com.cooksy.service.FavoriteService;
import com.cooksy.service.ShoppingListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v4/shoppinglist")
@AllArgsConstructor
public class ShoppingListBackDoor {
    private final ShoppingListService shoppingListService;


}
