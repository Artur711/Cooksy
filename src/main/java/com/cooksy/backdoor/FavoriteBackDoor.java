package com.cooksy.backdoor;

import com.cooksy.dto.FavoriteDto;
import com.cooksy.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/favorite")
@AllArgsConstructor
public class FavoriteBackDoor {
    private final FavoriteService favoriteService;

    @GetMapping
    public List<FavoriteDto> getAll(){
        return favoriteService.getAll();
    }

    @PostMapping
    public void addFavorite(@RequestBody FavoriteDto favoriteDto){
        favoriteService.addToFavorite(favoriteDto);
    }
}
