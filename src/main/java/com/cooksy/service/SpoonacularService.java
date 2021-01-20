package com.cooksy.service;

import com.cooksy.client.SpoonacularClient;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.RecipesDto;
import com.cooksy.model.api.SpCuRecipeDetails;
import com.cooksy.model.api.SpCuRecipes;
import com.cooksy.util.converter.api.SpCuRecipeDetailsToRecipeDetailsDtoConverter;
import com.cooksy.util.converter.api.SpCuRecipesToRecipesDtoConverter;
import com.cooksy.util.enums.ApiURL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpoonacularService {

    private final SpCuRecipeDetailsToRecipeDetailsDtoConverter recipeDetailsConverter;
    private final SpoonacularClient spoonacularClient;
    private final SpCuRecipesToRecipesDtoConverter recipesConverter;

    public RecipeDetailsDto getRecipeDetails(String id) {
        SpCuRecipeDetails recipeDetails = spoonacularClient.getSpCuRecipeDetails(id);
        return recipeDetailsConverter.convert(recipeDetails);
    }

    public RecipesDto getRecipes(String page, String ingredient, String equipment) {
        String recipesUrl = (page == null) ? ApiURL.RECIPES.getUrl() : getRecipesPage(page, ApiURL.RECIPES.getUrl());
        if (ingredient != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.INGREDIENT.getUrl(), ingredient);
        }
        if (equipment != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.EQUIPMENT.getUrl(), equipment);
        }
        SpCuRecipes recipes = spoonacularClient.callSpoonacularApi(SpCuRecipes.class, recipesUrl);
        return recipesConverter.convert(recipes);
    }

    public RecipesDto getRecipesVegetarian() {
        SpCuRecipes recipes = spoonacularClient.callSpoonacularApi(SpCuRecipes.class, ApiURL.VEGETARIAN.getUrl());
        return recipesConverter.convert(recipes);
    }

    private String getRecipesPage(String page, String url) {
        int valuePage = Integer.parseInt(page) - 1;
        return url + String.format(ApiURL.PAGE.getUrl(), valuePage * 10);
    }
}
