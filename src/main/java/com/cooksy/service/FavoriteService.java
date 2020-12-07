package com.cooksy.service;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.dto.Id;
import com.cooksy.exception.FavoriteNotFoundException;
import com.cooksy.model.Favorite;
import com.cooksy.model.User;
import com.cooksy.repository.FavoriteRepository;
import com.cooksy.util.converter.FavoriteDtoToFavoriteConverter;
import com.cooksy.util.converter.FavoriteToFavoriteDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteDtoToFavoriteConverter favoriteDtoToFavoriteConverter;
    private final FavoriteToFavoriteDtoConverter favoriteToFavoriteDtoConverter;

    public List<FavoriteDto> getAll() {
        List<FavoriteDto> favoritesDto = favoriteToFavoriteDtoConverter.convertAll((List<Favorite>) favoriteRepository.findAll());
        log.info(String.format("Returned %d favorites", favoritesDto.size()));
        return favoritesDto;
    }

    public List<FavoriteDto> getFavoritesByUser(Id id) {
        User user = new User();
        user.setUserId(id.getValue());
        List<FavoriteDto> favoritesDto = favoriteToFavoriteDtoConverter.convertAll(favoriteRepository.findByUser(user));
        log.info(String.format("Returned %d favorites by user id: %d from database", favoritesDto.size(), id.getValue()));
        return favoritesDto;
    }

    public void deleteFavorite(Id id) {
        favoriteRepository.deleteById(id.getValue());
        log.info(String.format("Deleted favorite by id: %d", id.getValue()));
    }

    public void addToFavorite(FavoriteDto favoriteDto) {
        favoriteRepository.save(favoriteDtoToFavoriteConverter.convert(favoriteDto));
        log.info(String.format("Added favorite: %s", favoriteDto.toString()));
    }

    public void updateFavorite(Id id, FavoriteDto favoriteDto) {
        Favorite favorite = favoriteDtoToFavoriteConverter.convert(favoriteDto);
        favorite.setFavoriteId(id.getValue());
        favoriteRepository.save(favorite);
        log.info(String.format("Updated favorite: %s", favorite.toString()));
    }

    public FavoriteDto getFavoriteById(Id id) {
        FavoriteDto favoriteDto= favoriteToFavoriteDtoConverter.convert(
                favoriteRepository.findById(id.getValue())
                        .orElseThrow(() -> new FavoriteNotFoundException(id)));

        log.info(String.format("Returned favorite by id: %d", id.getValue()));
        return favoriteDto;
    }
}
