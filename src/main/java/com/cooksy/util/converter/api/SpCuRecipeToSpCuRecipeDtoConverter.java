package com.cooksy.util.converter.api;

import com.cooksy.dto.api.SpCuRecipeDto;
import com.cooksy.model.api.SpCuRecipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpCuRecipeToSpCuRecipeDtoConverter {

    public SpCuRecipeDto convert(SpCuRecipe recipe) {
        return new SpCuRecipeDto(recipe.getId(), recipe.getTitle(), recipe.getImageUrl());
    }

    public List<SpCuRecipeDto> convertAll(List<SpCuRecipe> recipes) {
        return recipes.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
