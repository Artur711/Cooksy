package com.cooksy.service;

import com.cooksy.client.SpoonacularClient;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.RecipesDto;
import com.cooksy.model.api.SpCuParameters;
import com.cooksy.model.api.SpCuRecipeDetails;
import com.cooksy.model.api.SpCuRecipes;
import com.cooksy.util.converter.api.SpCuRecipeDetailsToRecipeDetailsDtoConverter;
import com.cooksy.util.converter.api.SpCuRecipesToRecipesDtoConverter;
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
        SpCuRecipeDetails recipeDetails = spoonacularClient.getSpCuRecipeDetails(id);
        return recipeDetailsConverter.convert(recipeDetails);
    }

    public RecipesDto getRecipes(SpCuParameters spCuParameters) throws
            InterruptedException, IOException, URISyntaxException {

        SpCuRecipes recipes = spoonacularClient.getSpCuRecipes(spCuParameters);
        return recipesConverter.convert(recipes);
    }
}
