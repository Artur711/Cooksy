package com.cooksy.util.converter;

import com.cooksy.dto.RecipeDto;
import com.cooksy.model.Product;
import com.cooksy.model.Recipe;
import com.cooksy.model.RecipeProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecipeDtoToRecipeConverter {
    private final RecipeProductDtoToRecipeProductConverter recipeProductDtoToRecipeProductConverter;


    public Recipe convert(RecipeDto recipeDto) {
        List<RecipeProduct> recipeProducts = recipeProductDtoToRecipeProductConverter.convertAll(recipeDto.getRecipeProducts());
        return new Recipe(recipeDto.getRecipeId(),
                recipeDto.getName(),
                recipeProducts,
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
