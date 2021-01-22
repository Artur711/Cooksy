package com.cooksy.util.enums;

public enum ApiURL {

    DETAILS("https://api.spoonacular.com/recipes/%s"),
    INFORMATION("/information?%s&includeNutrition=true"),
    RECIPES("https://api.spoonacular.com/recipes/complexSearch?%s"),
    INGREDIENT("&includeIngredients=%s"),
    EQUIPMENT("&equipment=%s"),
    PAGE("&offset=%s"),
    TYPE("&type=%s");

    private final String url;

    ApiURL(String url) {
        this.url = url;
    }

    public String getUrl(String parameter) {
        return (parameter != null) ? String.format(this.url, parameter.replaceAll(" ", "+")) : "";
    }
}
