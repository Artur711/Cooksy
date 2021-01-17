package com.cooksy.controller;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.dto.Id;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.UserDto;
import com.cooksy.service.FavoriteService;
import com.cooksy.service.RecipeService;
import com.cooksy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.cooksy.dto.Id.idFromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/favorites")
@Slf4j
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;
    private final RecipeService recipeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<FavoriteDto> getAll() {
        Id userId = Id.idFromLong(1L);
        log.info(String.format("Returned favorites by user [id: %d]", userId.getValue()));
        return favoriteService.getFavoritesByUser(userId);
    }

    @GetMapping(value = "/recipe/{id}", produces = APPLICATION_JSON_VALUE)
    public FavoriteDto getIsFavorite(@PathVariable String id) {
        Id userId = Id.idFromLong(1L);
        UserDto userDto = userService.getUserById(userId);
        RecipeDetailsDto recipeDetailsDto = recipeService.getRecipeById(Long.valueOf(id));
        if (!recipeDetailsDto.equals(new RecipeDetailsDto())) {
            FavoriteDto favorite = favoriteService.getFavoriteBuUserAndRecipe(userDto, recipeDetailsDto);
            log.info(String.format("Returned favorite [id: %d]", favorite.getFavoriteId()));
            return favorite;
        }
        log.info("Returned favorite empty object");
        return new FavoriteDto();
    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addFavorite(@RequestBody RecipeDetailsDto recipeDto) {
        Id userId = Id.idFromLong(1L);
        UserDto userDto = userService.getUserById(userId);
        favoriteService.addToFavorite(new FavoriteDto(0L, userDto, recipeDto));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        favoriteService.deleteFavorite(Id.idFromString(id));
    }
}
