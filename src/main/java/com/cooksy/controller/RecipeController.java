package com.cooksy.controller;

import com.cooksy.dto.RecipeDto;
import com.cooksy.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public List<RecipeDto> getAll() {
        return recipeService.getAll();
    }

    @GetMapping("/{id}")
    public RecipeDto getById(@PathVariable String id) {
        return recipeService.getRecipeById((Long.parseLong(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.saveRecipe(recipeDto);
    }

    @PutMapping("/{id}")
    public void updateRecipe(@PathVariable String recipeId, @RequestBody RecipeDto recipeDto) {
        recipeService.updateRecipe((Long.valueOf(recipeId)), recipeDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.deleteRecipe(recipeDto);
    }
}
