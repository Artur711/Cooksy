package com.cooksy.service;


import com.cooksy.dto.ShoppingListDto;
import com.cooksy.model.ShoppingList;
import com.cooksy.model.ShpList;
import com.cooksy.repository.ShoppingListRepository;

import com.cooksy.repository.ShpListRepository;
import com.cooksy.util.converter.ShoppingListDtoToShoppingListConverter;
import com.cooksy.util.converter.ShoppingListToShoppingListDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShpListRepository shpListRepository;
    private final ShoppingListDtoToShoppingListConverter shoppingListDtoToShoppingListConverter;
    private final ShoppingListToShoppingListDtoConverter shoppingListToShoppingListDtoConverter;


    public List<ShoppingListDto> getAll() {
        return shoppingListToShoppingListDtoConverter.convertAll((List<ShoppingList>) shoppingListRepository.findAll());
    }

    public void saveShoppingList(ShoppingListDto shoppingListDto) {
        shoppingListRepository.save(shoppingListDtoToShoppingListConverter.convert(shoppingListDto));
    }

    public void saveUsersShoppingList(ShpList shpList){
        shpListRepository.save(shpList);
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
