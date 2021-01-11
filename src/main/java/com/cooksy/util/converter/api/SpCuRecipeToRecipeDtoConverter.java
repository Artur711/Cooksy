package com.cooksy.util.converter.api;

import com.cooksy.dto.RecipeDto;
import com.cooksy.model.api.SpCuRecipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpCuRecipeToRecipeDtoConverter {

    public RecipeDto convert(SpCuRecipe recipe) {
        return new RecipeDto(recipe.getId(), recipe.getTitle(), recipe.getImageUrl());
    }

    public List<RecipeDto> convertAll(List<SpCuRecipe> recipes) {
        return recipes.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
