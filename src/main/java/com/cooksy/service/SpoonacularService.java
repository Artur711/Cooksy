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

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@AllArgsConstructor
public class SpoonacularService {

    private final SpCuRecipeDetailsToRecipeDetailsDtoConverter recipeDetailsConverter;
    private final SpoonacularClient spoonacularClient;
    private final SpCuRecipesToRecipesDtoConverter recipesConverter;

    public RecipeDetailsDto getRecipeDetails(String id) throws InterruptedException, IOException, URISyntaxException {
        String url = String.format(ApiURL.DETAILS.getUrl(), id, "%s");
        SpCuRecipeDetails recipeDetails = spoonacularClient.getObject(SpCuRecipeDetails.class, url);
        return recipeDetailsConverter.convert(recipeDetails);
    }

    public RecipesDto getRecipes(String page, String ingredients, String equipments, String types) throws
            InterruptedException, IOException, URISyntaxException {
        String recipesUrl = (page == null) ? ApiURL.RECIPES.getUrl() : getRecipesPage(page, ApiURL.RECIPES.getUrl());
        if (ingredients != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.INGREDIENT.getUrl(), replaceSpaces(ingredients));
        }
        if (equipments != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.EQUIPMENT.getUrl(), replaceSpaces(equipments));
        }
        if (types != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.TYPE.getUrl(), replaceSpaces(types));
        }

        SpCuRecipes recipes = spoonacularClient.getObject(SpCuRecipes.class, recipesUrl);
        return recipesConverter.convert(recipes);
    }

    public RecipesDto getRecipesVegetarian() throws InterruptedException, IOException, URISyntaxException {
        SpCuRecipes recipes = spoonacularClient.getObject(SpCuRecipes.class, ApiURL.VEGETARIAN.getUrl());
        return recipesConverter.convert(recipes);
    }

    private String getRecipesPage(String page, String url) {
        int valuePage = Integer.parseInt(page) - 1;
        return url + String.format(ApiURL.PAGE.getUrl(), valuePage * 10);
    }

    private String replaceSpaces(String str) {
        return str.replaceAll(" ", "+");
    }
}
