package com.cooksy.repository;


import com.cooksy.model.ShoppingList;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

}
