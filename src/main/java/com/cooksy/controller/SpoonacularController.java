package com.cooksy.controller;

import com.cooksy.client.SpoonacularClient;
import com.cooksy.dto.api.SpCuRecipeDetailsDto;
import com.cooksy.dto.api.SpCuRecipeDto;
import com.cooksy.dto.api.SpCuRecipesDto;
import com.cooksy.model.api.SpCuRecipeDetails;
import com.cooksy.model.api.SpCuRecipes;
import com.cooksy.service.SpoonacularService;
import com.cooksy.util.converter.api.SpCuRecipeDetailsToSpCuRecipeDetailsConverter;
import com.cooksy.util.converter.api.SpCuRecipesToSpCuRecipesDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipes")
public class SpoonacularController {

    private final SpCuRecipeDetailsToSpCuRecipeDetailsConverter recipeDetailsConverter;
    private final SpCuRecipesToSpCuRecipesDtoConverter recipesConverter;

    private final SpoonacularService service;
    private final SpoonacularClient spoonacularClient;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    private SpCuRecipesDto getRecipesRandom() {
        SpCuRecipes recipes = spoonacularClient.getObject(SpCuRecipes.class, service.getRecipes());
        SpCuRecipesDto recipesDto = recipesConverter.convert(recipes);
        return recipesDto;
    }

    @GetMapping(value = "/vegetarian", produces = APPLICATION_JSON_VALUE)
    public SpCuRecipesDto getRecipesVegetarian() {
        SpCuRecipes recipes = spoonacularClient.getObject(SpCuRecipes.class, service.getRecipesVegetarian());
        return recipesConverter.convert(recipes);
    }

    @GetMapping(value = "/recipe-details/{id}", produces = APPLICATION_JSON_VALUE)
    public SpCuRecipeDetailsDto getRecipe(@PathVariable("id") String id) {
        SpCuRecipeDetails recipeDetails = spoonacularClient.getObject(SpCuRecipeDetails.class, service.getRecipeDetails(id));
        return recipeDetailsConverter.convert(recipeDetails);
    }

    @GetMapping(value = "/{ingredient}", produces = APPLICATION_JSON_VALUE)
    public SpCuRecipesDto getRecipes(@PathVariable("ingredient") String ingredient) {
        SpCuRecipes recipes = spoonacularClient.getObject(SpCuRecipes.class, service.getRecipesIngredient(ingredient));
        return recipesConverter.convert(recipes);
    }
}

