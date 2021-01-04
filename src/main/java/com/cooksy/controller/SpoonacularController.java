package com.cooksy.controller;

import com.cooksy.client.SpoonacularClient;
import com.cooksy.dto.api.RecipeDetails;
import com.cooksy.dto.api.Recipes;
import com.cooksy.service.SpoonacularService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class SpoonacularController {

    private final SpoonacularService service;
    private final SpoonacularClient spoonacularClient;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    private Recipes getRecipesRandom() {
        return spoonacularClient.getObject(Recipes.class, service.getRecipes());
    }

    @GetMapping(value = "/vegetarian", produces = APPLICATION_JSON_VALUE)
    public Recipes getJson() {
        return spoonacularClient.getObject(Recipes.class, service.getRecipesVegetarian());
    }

    @GetMapping(value = "/recipe-details/{id}", produces = APPLICATION_JSON_VALUE)
    public RecipeDetails getRecipe(@PathVariable("id") String id) {
        return spoonacularClient.getObject(RecipeDetails.class, service.getRecipeDetails(id));
    }

    @GetMapping(value = "/{ingredient}", produces = APPLICATION_JSON_VALUE)
    public Recipes getRecipes(@PathVariable("ingredient") String ingredient) {
        return spoonacularClient.getObject(Recipes.class, service.getRecipesIngredient(ingredient));
    }
}
