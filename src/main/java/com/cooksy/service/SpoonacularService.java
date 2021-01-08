package com.cooksy.service;

import com.cooksy.util.enums.ApiURL;
import org.springframework.stereotype.Service;

@Service
public class SpoonacularService {

    public String getRecipes() {
        return ApiURL.RECIPES.getUrl();
    }

    public String getRecipesPage(String page) {
        int valuePage = Integer.parseInt(page) - 1;
        return getRecipes() + String.format(ApiURL.PAGE.getUrl(), valuePage * 10);
    }

    public String getRecipesVegetarian() {
        return ApiURL.VEGETARIAN.getUrl();
    }

    public String getRecipeDetails(String id) {
        return String.format(ApiURL.DETAILS.getUrl(), id, "%s");
    }

    public String getRecipesIngredient(String ingredient) {
        return String.format(ApiURL.INGREDIENT.getUrl(), "%s", ingredient);
    }
}
