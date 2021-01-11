package com.cooksy.controller;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.UserDto;
import com.cooksy.service.FavoriteService;
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

/*    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addFavorite(@RequestBody FavoriteDto favoriteDto) {
        favoriteService.addToFavorite(favoriteDto);
    }*/


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addFavorite(@RequestBody RecipeDetailsDto recipeDto) {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);

        FavoriteDto favoriteDto = new FavoriteDto(0L, userDto, recipeDto);

        favoriteService.addToFavorite(favoriteDto);
        System.out.println(recipeDto.toString());
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void updateFavorite(@PathVariable String id, @RequestBody FavoriteDto favoriteDto) {
        favoriteService.updateFavorite(idFromString(id), favoriteDto);
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void deleteFavorite(@PathVariable String id) {
        favoriteService.deleteFavorite(idFromString(id));
    }
}
