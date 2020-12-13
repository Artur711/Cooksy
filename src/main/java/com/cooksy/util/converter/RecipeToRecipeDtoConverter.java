package com.cooksy.util.converter;

import com.cooksy.dto.RecipeDto;
import com.cooksy.dto.RecipeProductDto;
import com.cooksy.model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecipeToRecipeDtoConverter {
    private final RecipeProductToRecipeProductDtoConverter recipeProductToRecipeProductDtoConverter;

    public RecipeDto convert(Recipe recipe) {
        List<RecipeProductDto> recipeProductDtos = recipeProductToRecipeProductDtoConverter.convertAll(recipe.getRecipeProducts());
        return new RecipeDto(recipe.getRecipeId(),
                recipe.getName(),
                recipeProductDtos,
                recipe.getDescription(),
                recipe.getPhotoUrl(),
                recipe.getAuthor());
    }

    public List<RecipeDto> convertAll(List<Recipe> recipeDtos) {
        return recipeDtos.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
