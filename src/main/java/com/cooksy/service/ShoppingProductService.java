package com.cooksy.service;

import com.cooksy.exception.ProductsToDbException;
import com.cooksy.model.*;
import com.cooksy.repository.ShoppingProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class ShoppingProductService {
    private ShoppingProductRepository shoppingProductRepository;
    private ShoppingListService shoppingListService;


    public void saveProductsToShoppingList(List<Product> products, User user, String date) {
        try {
            ShpList shpList = shoppingListService.saveUsersShoppingList(new ShpList(date));
            products.forEach(product -> this.shoppingProductRepository.save(new ShoppingProduct(user, product, shpList, product.getMeasuresAmount())));
        } catch (ProductsToDbException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<List<ShoppingProduct>> getUserLists(User user) {
        List<ShoppingProduct> allUsersProducts = this.shoppingProductRepository.findAllByUser(user);
        return new ArrayList<>(allUsersProducts.stream()
                .collect(Collectors.groupingBy(ShoppingProduct::getShpList, Collectors.toList()))
                .values());
    }

}
