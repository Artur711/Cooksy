package com.cooksy.util.converter;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.model.Favorite;
import com.cooksy.model.Recipe;
import com.cooksy.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FavoriteDtoToFavoriteConverter {

    private final UserDtoToUserConverter userDtoToUserConverter;
    private final RecipeDetailsDtoToRecipeConverter recipeDetailsDtoToRecipeConverter;

    public Favorite convert(FavoriteDto favoriteDto){
        User user = userDtoToUserConverter.convert(favoriteDto.getUser());
        Recipe recipe = recipeDetailsDtoToRecipeConverter.convert(favoriteDto.getRecipe());
        return new Favorite(favoriteDto.getFavoriteId(), user, recipe);
    }

    public List<Favorite> convertAll(List<FavoriteDto> favoritesDto){
        return favoritesDto.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
