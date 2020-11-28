package com.cooksy.util.converter;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.model.Favorite;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class FavoriteToFavoriteDtoConverter {

    public FavoriteDto convert(Favorite favorite){
        return new FavoriteDto(favorite.getFavoriteId(),
                favorite.getUser().getUserId(),
                favorite.getRecipe().getRecipeId());
    }

    public List<FavoriteDto> convertAll(List<Favorite> favorites){
        return favorites.stream().map(this::convert).collect(Collectors.toList());
    }
}
