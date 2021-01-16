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
        String url = String.format(ApiURL.DETAILS.getUrl(), id, "%s");
        SpCuRecipeDetails recipeDetails = spoonacularClient.getObject(SpCuRecipeDetails.class, url);
        return recipeDetailsConverter.convert(recipeDetails);
    }

    public RecipesDto getRecipes(String page) {
        SpCuRecipes recipes = (page == null) ?
                spoonacularClient.getObject(SpCuRecipes.class, ApiURL.RECIPES.getUrl()) :
                spoonacularClient.getObject(SpCuRecipes.class, getRecipesPage(page, ApiURL.RECIPES.getUrl()));

        return recipesConverter.convert(recipes);
    }

    public RecipesDto getRecipesVegetarian() {
        SpCuRecipes recipes = spoonacularClient.getObject(SpCuRecipes.class, ApiURL.VEGETARIAN.getUrl());
        return recipesConverter.convert(recipes);
    }

    public RecipesDto getRecipesIngredient(String ingredient, String page) {
        String url = String.format(ApiURL.INGREDIENT.getUrl(), "%s", ingredient);
        SpCuRecipes recipes = (page == null || page.equals("") ?
                spoonacularClient.getObject(SpCuRecipes.class, url) :
                spoonacularClient.getObject(SpCuRecipes.class, getRecipesPage(page, url)));
        return recipesConverter.convert(recipes);
    }

    private String getRecipesPage(String page, String url) {
        int valuePage = Integer.parseInt(page) - 1;
        return url + String.format(ApiURL.PAGE.getUrl(), valuePage * 10);
    }
}
