package com.cooksy.util;

import com.cooksy.dto.RecipeDto;
import com.cooksy.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeToRecipeDtoConverter {

    public RecipeDto convert(Recipe recipe) {
        return new RecipeDto(recipe.getRecipeId(),
                recipe.getProductID(),
                recipe.getDescription(),
                recipe.getPhotoUrl(),
                recipe.getAuthor());
    }

    public List<RecipeDto> convertAll(List<Recipe> recipeDtos) {
        return recipeDtos.stream().map(this::convert).collect(Collectors.toList());
    }
}
