package com.cooksy.backdoor;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v4/favorites")
@AllArgsConstructor
public class FavoriteBackDoor {
    private final FavoriteService favoriteService;

    @GetMapping
    public List<FavoriteDto> getAll(){
        return favoriteService.getAll();
    }

    @GetMapping("/{id}")
    public FavoriteDto getFavorite(@PathVariable String id) {
        return favoriteService.getFavoriteById(Long.valueOf(id));
    }

    @GetMapping("/user/{userId}")
    public List<FavoriteDto> getFavoritesByUser(@PathVariable String userId) {
        return favoriteService.getFavoritesByUser(Long.valueOf(userId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addFavorite(@RequestBody FavoriteDto favoriteDto){
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
