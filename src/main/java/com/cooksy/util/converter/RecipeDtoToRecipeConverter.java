package com.cooksy.util.converter;

import com.cooksy.dto.RecipeDto;
import com.cooksy.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeDtoToRecipeConverter {

    public Recipe convert(RecipeDto recipeDto) {
        return new Recipe(recipeDto.getRecipeId(),
                recipeDto.getProducts(),
                recipeDto.getDescription(),
                recipeDto.getPhotoUrl(),
                recipeDto.getAuthor());
    }

    public List<Recipe> convertAll(List<RecipeDto> recipeDtos) {
        return recipeDtos.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
