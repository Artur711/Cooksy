package com.cooksy.controller;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.dto.Id;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.UserDto;
import com.cooksy.service.FavoriteService;
import com.cooksy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cooksy.dto.Id.idFromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/favorites")
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<FavoriteDto> getAll(@RequestParam(required = false) String userId) {
        if (userId != null) {
            return favoriteService.getFavoritesByUser(idFromString(userId));
        } else {
            return favoriteService.getAll();
        }
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public FavoriteDto getFavorite(@PathVariable String id) {
        return favoriteService.getFavoriteById(idFromString(id));
    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addFavorite(@RequestBody RecipeDetailsDto recipeDto) {
        UserDto userDto = userService.getUserById(idFromString("1"));
        favoriteService.addToFavorite(new FavoriteDto(0L, userDto, recipeDto));
    }


    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void deleteFavorite() {
        UserDto userDto = userService.getUserById(idFromString("1"));
        favoriteService.deleteFavorite(1L);
    }
}
