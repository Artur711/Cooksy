package com.cooksy.controller;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.dto.Id;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.UserDto;
import com.cooksy.service.DecodeTokenService;
import com.cooksy.service.FavoritesService;
import com.cooksy.service.RecipeService;
import com.cooksy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"http://localhost:4200", "https://cooksy-frontend.herokuapp.com"})
@RestController
@RequestMapping("/api/v1/favorites")
@Slf4j
@AllArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;
    private final UserService userService;
    private final RecipeService recipeService;
    private final DecodeTokenService decodeTokenService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<FavoriteDto> getAll(@RequestHeader("Authorization") String headerValue) {
        Id userId = Id.idFromLong(decodeTokenService.getUserIdFromToken(headerValue));
        log.info(String.format("Returned favorites by user [id: %d]", userId.getValue()));
        return favoritesService.getFavoritesByUser(userId);
    }

    @GetMapping(value = "/recipe/{id}", produces = APPLICATION_JSON_VALUE)
    public FavoriteDto getIsFavorite(@PathVariable String id, @RequestHeader("Authorization") String headerValue) {
        Id userId = Id.idFromLong(decodeTokenService.getUserIdFromToken(headerValue));
        UserDto userDto = userService.getUserById(userId);
        RecipeDetailsDto recipeDetailsDto = recipeService.getRecipeById(Long.valueOf(id));

        if (!recipeDetailsDto.equals(new RecipeDetailsDto())) {
            FavoriteDto favorite = favoritesService.getFavoriteByUserAndRecipe(userDto, recipeDetailsDto);
            log.info(String.format("Returned favorite [id: %d]", favorite.getFavoriteId()));
            return favorite;
        }
        log.info("Returned favorite empty object");
        return new FavoriteDto();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addFavorite(@RequestBody RecipeDetailsDto recipeDto,
                            @RequestHeader("Authorization") String headerValue) {
        Id userId = Id.idFromLong(decodeTokenService.getUserIdFromToken(headerValue));
        UserDto userDto = userService.getUserById(userId);
        favoritesService.addToFavorite(new FavoriteDto(0L, userDto, recipeDto));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("id") String id, @RequestHeader("Authorization") String headerValue) {
        Id userId = Id.idFromLong(decodeTokenService.getUserIdFromToken(headerValue));
        favoritesService.deleteFavorite(Id.idFromString(id), userId);
    }
}
