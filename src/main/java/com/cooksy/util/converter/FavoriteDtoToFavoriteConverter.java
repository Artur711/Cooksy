package com.cooksy.util.converter;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.model.Favorite;
import com.cooksy.service.RecipeService;
import com.cooksy.service.UserService;
import com.cooksy.util.converter.RecipeDtoToRecipeConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FavoriteDtoToFavoriteConverter {
    private final UserService userService;
    private final RecipeService recipeService;
    private final UserDtoToUserConverter userDtoToUserConverter;
    private final RecipeDtoToRecipeConverter  recipeDtoToRecipeConverter;

    public Favorite convert(FavoriteDto favoriteDto){
        return new Favorite(favoriteDto.getFavoriteId(),
           userDtoToUserConverter.convert(userService.getUserById(favoriteDto.getUserId())),
             recipeDtoToRecipeConverter.convert(recipeService.getRecipeById(favoriteDto.getRecipeId())));
    }

    public List<Favorite> convertAll(List<FavoriteDto> favoriteDtos){
        return favoriteDtos.stream().map(this::convert).collect(Collectors.toList());
    }
}
