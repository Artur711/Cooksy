package com.cooksy.util.enums;

public enum ApiURL {

    VEGETARIAN("https://api.spoonacular.com/recipes/complexSearch?%s&diet=vegetarian"),
    DETAILS("https://api.spoonacular.com/recipes/%s/information?%s&includeNutrition=true"),
    RECIPES("https://api.spoonacular.com/recipes/complexSearch?%s&"),
    INGREDIENT("https://api.spoonacular.com/recipes/complexSearch?%s&includeIngredients=%s");

    private String url;

    ApiURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
