package com.cooksy.controller;

import com.cooksy.client.SpoonacularClient;
import com.cooksy.dto.api.RecipeDetails;
import com.cooksy.dto.api.Recipes;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SpoonacularController {


    private final SpoonacularClient spoonacularClient;

    @GetMapping("/json")
    public Recipes getJson() {
            return spoonacularClient.getObject(Recipes.class, getRecipesVegetarian());
    }

    @GetMapping("/recipe-details/{id}")
    public RecipeDetails getRecipe(@PathVariable("id") String id) {
        return spoonacularClient.getObject(RecipeDetails.class, getRecipeDetails(id));
    }

    @GetMapping("/recipes/{ingredient}")
    public Recipes getRecipes(@PathVariable("ingredient") String ingredient) {
        return spoonacularClient.getObject(Recipes.class, getRecipes2(ingredient));
    }

    private String getRecipes2(String ingredient) {
        return String.format("https://api.spoonacular.com/recipes/complexSearch?%s&includeIngredients=%s.", "%s", ingredient);
    }

    private String getRecipesVegetarian() {
        return "https://api.spoonacular.com/recipes/complexSearch?%s&diet=vegetarian";
    }

    private String getRecipeDetails(String id) {
        return String.format("https://api.spoonacular.com/recipes/%s/information?%s&includeNutrition=true.", id, "%s");
    }
}
