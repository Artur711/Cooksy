package com.cooksy.controller;

import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.RecipesDto;
import com.cooksy.service.RecipeService;
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
    private final RecipeService recipeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    private RecipesDto getRecipes(@RequestParam(required = false) String page,
                                  @RequestParam(required = false) String ingredient,
                                  @RequestParam(required = false) String equipment,
                                  @RequestParam(required = false) String type) {
        return service.getRecipes(page, ingredient, equipment, type);
    }

    @GetMapping(value = "/vegetarian", produces = APPLICATION_JSON_VALUE)
    public RecipesDto getRecipesVegetarian() {
        return service.getRecipesVegetarian();
    }

    @GetMapping(value = "/recipe-detail/{id}", produces = APPLICATION_JSON_VALUE)
    public RecipeDetailsDto getRecipe(@PathVariable("id") String id) {
        RecipeDetailsDto recipeById = recipeService.getRecipeById(Long.valueOf(id));
        return (recipeById.equals(new RecipeDetailsDto())) ? service.getRecipeDetails(id) : recipeById;
    }
}
