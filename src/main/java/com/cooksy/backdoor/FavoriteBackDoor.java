package com.cooksy.backdoor;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.service.FavoriteService;
import com.cooksy.service.UserId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cooksy.service.UserId.idFromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v4/favorites")
@AllArgsConstructor
public class FavoriteBackDoor {
    private final FavoriteService favoriteService;

    /**
     * To test if it is possible to call this endpoints separately, one with request param and another without
     *
     * @GetMapping public List<FavoriteDto> getAll(@RequestParam(required = false) String userId){
     * if (userId != null){
     * return favoriteService.getFavoritesByUser(Long.valueOf(userId));
     * } else {
     * return favoriteService.getAll();
     * }
     * }
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<FavoriteDto> getAll(@RequestParam(required = false) String userId) {
        if (userId != null) {
            return favoriteService.getFavoritesByUser(idFromString(userId));
        } else {
            return favoriteService.getAll();
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<FavoriteDto> getFavoritesByUser(@RequestParam String userId) {
        return favoriteService.getFavoritesByUser(Long.valueOf(userId));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public FavoriteDto getFavorite(@PathVariable String id) {
        return favoriteService.getFavoriteById(Long.valueOf(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addFavorite(@RequestBody FavoriteDto favoriteDto) {
        favoriteService.addToFavorite(favoriteDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateFavorite(@PathVariable String id, @RequestBody FavoriteDto favoriteDto) {
        favoriteService.updateFavorite(Long.valueOf(id), favoriteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteFavorite(@PathVariable String id) {
        favoriteService.deleteFavorite(Long.valueOf(id));
    }
}
