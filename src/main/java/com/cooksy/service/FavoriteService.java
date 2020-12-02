package com.cooksy.service;

import com.cooksy.dto.FavoriteDto;
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
        log.info("Starts getting all favorites from database");
        return favoriteToFavoriteDtoConverter.convertAll((List<Favorite>) favoriteRepository.findAll());
    }

    public List<FavoriteDto> getFavoritesByUser(Long userId) {
        User user = new User();
        user.setUserId(userId);
        log.info(String.format("Starts getting all favorites by user id: %d from database", userId));
        return favoriteToFavoriteDtoConverter.convertAll(favoriteRepository.findByUser(user));
    }

    public void deleteFavorite(Long favoriteId) {
        log.info(String.format("Starts delete favorite by id: %d",favoriteId));
        favoriteRepository.deleteById(favoriteId);
    }

    public void addToFavorite(FavoriteDto favoriteDto) {
        log.info(String.format("Starts save favorite: %s", favoriteDto.toString()));
        favoriteRepository.save(favoriteDtoToFavoriteConverter.convert(favoriteDto));
    }

    public void updateFavorite(Long favoriteId, FavoriteDto favoriteDto) {
        Favorite favorite = favoriteDtoToFavoriteConverter.convert(favoriteDto);
        favorite.setFavoriteId(favoriteId);
        log.info(String.format("Starts save favorite: %s", favorite.toString()));
        favoriteRepository.save(favorite);
    }

    public FavoriteDto getFavoriteById(Long favoriteId) {
        log.info(String.format("Starts find favorite by id: %d", favoriteId));
        return favoriteToFavoriteDtoConverter.convert(
                favoriteRepository.findById(favoriteId)
                        .orElseThrow( () -> new FavoriteNotFoundException(favoriteId)));
    }
}
