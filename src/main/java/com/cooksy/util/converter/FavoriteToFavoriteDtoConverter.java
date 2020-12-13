package com.cooksy.util.converter;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.dto.RecipeDto;
import com.cooksy.dto.UserDto;
import com.cooksy.model.Favorite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FavoriteToFavoriteDtoConverter {

    private UserToUserDtoConverter userToUserDtoConverter;
    private RecipeToRecipeDtoConverter recipeToRecipeDtoConverter;

    public FavoriteDto convert(Favorite favorite){
        UserDto userDto = userToUserDtoConverter.convert(favorite.getUser());
        RecipeDto recipeDto = recipeToRecipeDtoConverter.convert(favorite.getRecipe());

        return new FavoriteDto(favorite.getFavoriteId(),
                userDto,
                recipeDto);
    }

    public List<FavoriteDto> convertAll(List<Favorite> favorites){
        return favorites.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
