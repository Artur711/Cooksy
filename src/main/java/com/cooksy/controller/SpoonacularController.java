package com.cooksy.controller;

import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.RecipesDto;
import com.cooksy.service.SpoonacularService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipes")
public class SpoonacularController {

    private final SpoonacularService service;


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    private RecipesDto getRecipes(@RequestParam(required = false) String page) {
        return service.getRecipes(page);
    }

    @GetMapping(value = "/vegetarian", produces = APPLICATION_JSON_VALUE)
    public RecipesDto getRecipesVegetarian() {
        return service.getRecipesVegetarian();
    }

    @GetMapping(value = "/recipe-detail/{id}", produces = APPLICATION_JSON_VALUE)
    public RecipeDetailsDto getRecipe(@PathVariable("id") String id) {
        return service.getRecipeDetails(id);
    }

    @GetMapping(value = "/{ingredient}", produces = APPLICATION_JSON_VALUE)
    public RecipesDto getRecipesIngredient(@PathVariable("ingredient") String ingredient) {
        return service.getRecipesIngredient(ingredient);
    }
}
