package com.cooksy.service;


import com.cooksy.dto.Id;
import com.cooksy.dto.ShoppingListDto;
import com.cooksy.exception.NotFoundException;
import com.cooksy.model.ShpList;
import com.cooksy.repository.ShoppingListRepository;

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
    private final ShoppingListDtoToShoppingListConverter shoppingListDtoToShoppingListConverter;
    private final ShoppingListToShoppingListDtoConverter shoppingListToShoppingListDtoConverter;


    public List<ShoppingListDto> getAll() {
        return shoppingListToShoppingListDtoConverter.convertAll((List<ShpList>) shoppingListRepository.findAll());
    }

    public void saveShoppingList(ShoppingListDto shoppingListDto) {
        shoppingListRepository.save(shoppingListDtoToShoppingListConverter.convert(shoppingListDto));
    }

    public void saveUsersShoppingList(ShpList shpList) {
        shoppingListRepository.save(shpList);
    }

    public ShoppingListDto getShoppingListByID(Id id) {
        return shoppingListToShoppingListDtoConverter.convert(shoppingListRepository.findById(id.getValue())
                .orElseThrow(() -> new NotFoundException("Shopping List", id)));
    }

    public void deleteShoppingList(ShoppingListDto shoppingListDto) {
        shoppingListRepository.delete(shoppingListDtoToShoppingListConverter.convert(shoppingListDto));
    }


}
