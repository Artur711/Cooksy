package com.cooksy.service;


import com.cooksy.dto.ProductDto;
import com.cooksy.dto.ShoppingListDto;
import com.cooksy.model.Product;
import com.cooksy.model.ShoppingList;
import com.cooksy.repository.ShoppingListRepository;

import com.cooksy.util.converter.ShoppingListDtoToShoppingListConverter;
import com.cooksy.util.converter.ShoppingListToShoppingListDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Data
@AllArgsConstructor
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListDtoToShoppingListConverter shoppingListDtoToShoppingListConverter;
    private final ShoppingListToShoppingListDtoConverter shoppingListToShoppingListDtoConverter;


    public List<ShoppingListDto> getAll() {
        return shoppingListToShoppingListDtoConverter.convertAll((List<ShoppingList>) shoppingListRepository.findAll());
    }

    public void saveShoppingList(ShoppingListDto shoppingListDto) {
        shoppingListRepository.save(shoppingListDtoToShoppingListConverter.convert(shoppingListDto));
    }

    public ShoppingListDto getShoppingListByID(Long id) {
        if (shoppingListRepository.findById(id).isPresent()) {
            return shoppingListToShoppingListDtoConverter.convert(shoppingListRepository.findById(id).get());
        }
        return null;  //??
    }

    public void deleteShoppingList(ShoppingListDto shoppingListDto) {
        shoppingListRepository.delete(shoppingListDtoToShoppingListConverter.convert(shoppingListDto));
    }


}
