package com.cooksy.service;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.model.Favorite;
import com.cooksy.repository.FavoriteRepository;
import com.cooksy.util.converter.FavoriteDtoToFavoriteConverter;
import com.cooksy.util.converter.FavoriteToFavoriteDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteDtoToFavoriteConverter favoriteDtoToFavoriteConverter;
    private final FavoriteToFavoriteDtoConverter favoriteToFavoriteDtoConverter;

    public List<FavoriteDto> getAll() {
        return favoriteToFavoriteDtoConverter.convertAll((List<Favorite>) favoriteRepository.findAll());
    }

    public void deleteFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    public void addToFavorite(FavoriteDto favoriteDto) {
        favoriteRepository.addRecipeToUserFavorite(favoriteDtoToFavoriteConverter.convert(favoriteDto).getRecipe().getRecipeId(),
                favoriteDtoToFavoriteConverter.convert(favoriteDto).getUser().getUserId());
    }
}
