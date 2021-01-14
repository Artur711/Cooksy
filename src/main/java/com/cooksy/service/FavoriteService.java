package com.cooksy.service;

import com.cooksy.dto.*;
import com.cooksy.exception.FavoriteNotFoundException;
import com.cooksy.model.Favorite;
import com.cooksy.model.Product;
import com.cooksy.model.Recipe;
import com.cooksy.model.User;
import com.cooksy.repository.FavoriteRepository;
import com.cooksy.repository.ProductRepository;
import com.cooksy.repository.RecipeRepository;
import com.cooksy.util.converter.FavoriteDtoToFavoriteConverter;
import com.cooksy.util.converter.FavoriteToFavoriteDtoConverter;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import com.cooksy.util.converter.UserDtoToUserConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteDtoToFavoriteConverter favoriteDtoToFavoriteConverter;
    private final FavoriteToFavoriteDtoConverter favoriteToFavoriteDtoConverter;
    private final UserService userService;
    private final UserDtoToUserConverter userDtoToUserConverter;
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final ProductDtoToProductConverter productDtoToProductConverter;

    public List<FavoriteDto> getAll() {
        List<FavoriteDto> favoritesDto = favoriteToFavoriteDtoConverter.convertAll((List<Favorite>) favoriteRepository.findAll());
        log.info(String.format("Returned %d favorites", favoritesDto.size()));
        return favoritesDto;
    }

    public List<FavoriteDto> getFavoritesByUser(Id id) {
        User user = userDtoToUserConverter.convert(userService.getUserById(id));
        List<FavoriteDto> favoritesDto = favoriteToFavoriteDtoConverter.convertAll(favoriteRepository.findByUser(user));
        log.info(String.format("Returned %d favorites by user id: %d from database", favoritesDto.size(), id.getValue()));
        return favoritesDto;
    }

    public void addToFavorite(FavoriteDto favoriteDto) {
        Favorite favorite = favoriteDtoToFavoriteConverter.convert(favoriteDto);
        Optional<Favorite> maybeFavorite = favoriteRepository.findByUserAndAndRecipe(favorite.getUser(), favorite.getRecipe());

        if (maybeFavorite.isEmpty()) {
//            productRepository.saveAll(favorite.getRecipe().getProducts());
//            recipeRepository.save(favorite.getRecipe());
            favoriteRepository.save(favorite);
            log.info(String.format("Added favorite [id: %d, userId: %d, recipeId: %d]", favoriteDto.getFavoriteId(),
                    favoriteDto.getUser().getUserId(), favoriteDto.getRecipe().getRecipeId()));
        }
    }

    public void deleteFavorite(Id id) {
//        FavoriteDto favoriteDto = getFavoriteById(id);
//        productRepository.deleteAll(productDtoToProductConverter.convertAll(favoriteDto.getRecipe().getProducts()));

       favoriteRepository.deleteById(id.getValue());
        log.info(String.format("Deleted favorite [id: %d]", id.getValue()));
    }

    public FavoriteDto getFavoriteBuUserAndRecipe(UserDto userDto, RecipeDetailsDto recipeDto) {
        Favorite favorite = favoriteDtoToFavoriteConverter.convert(new FavoriteDto(0L, userDto, recipeDto));
        Optional<Favorite> maybeFavorite = favoriteRepository.findByUserAndAndRecipe(favorite.getUser(), favorite.getRecipe());
        FavoriteDto favoriteDto = new FavoriteDto();
        return (maybeFavorite.isPresent()) ? favoriteToFavoriteDtoConverter.convert(maybeFavorite.get()) : new FavoriteDto();
    }

    public FavoriteDto getFavoriteByRecipeId(Id id) {
        FavoriteDto favoriteDto= favoriteToFavoriteDtoConverter.convert(
                favoriteRepository.findById(id.getValue())
                        .orElseThrow(() -> new FavoriteNotFoundException(id)));

        log.info(String.format("Returned favorite by id: %d", id.getValue()));
        return favoriteDto;
    }
}
