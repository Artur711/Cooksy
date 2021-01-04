package com.cooksy.controller;

import com.cooksy.client.SpoonacularClient;
import com.cooksy.dto.api.RecipeDetails;
import com.cooksy.dto.api.Recipes;
import com.cooksy.service.SpoonacularService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class SpoonacularController {

    private final SpoonacularService service;
    private final SpoonacularClient spoonacularClient;

    @GetMapping
    private Recipes getRecipesRandom() {
        return spoonacularClient.getObject(Recipes.class, service.getRecipes());
    }

    @GetMapping("/vegetarian")
    public Recipes getJson() {
        return spoonacularClient.getObject(Recipes.class, service.getRecipesVegetarian());
    }

    @GetMapping("/recipe-details/{id}")
    public RecipeDetails getRecipe(@PathVariable("id") String id) {
        return spoonacularClient.getObject(RecipeDetails.class, service.getRecipeDetails(id));
    }

    @GetMapping("/{ingredient}")
    public Recipes getRecipes(@PathVariable("ingredient") String ingredient) {
        return spoonacularClient.getObject(Recipes.class, service.getRecipesIngredient(ingredient));
    }
}
