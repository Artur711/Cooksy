package com.cooksy.util.converter;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.model.Favorite;
import com.cooksy.model.Recipe;
import com.cooksy.model.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoriteDtoToFavoriteConverter {

    public Favorite convert(FavoriteDto favoriteDto){
        User user = new User();
        user.setUserId(favoriteDto.getUserId());
        Recipe recipe = new Recipe();
        recipe.setRecipeId(favoriteDto.getRecipeId());

        return new Favorite(favoriteDto.getFavoriteId(), user, recipe);
    }

    public List<Favorite> convertAll(List<FavoriteDto> favoriteDtos){
        return favoriteDtos.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
