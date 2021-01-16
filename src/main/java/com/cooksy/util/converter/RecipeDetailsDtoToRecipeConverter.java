package com.cooksy.util.converter;

import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.model.Product;
import com.cooksy.model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecipeDetailsDtoToRecipeConverter {
    private final ProductDtoToProductConverter productDtoToRecipeProductConverter;

    public Recipe convert(RecipeDetailsDto recipeDetailsDto) {
        List<Product> recipeProducts = productDtoToRecipeProductConverter.convertAll(recipeDetailsDto.getProducts());
        return new Recipe(recipeDetailsDto.getRecipeId(),
                recipeDetailsDto.getTittle(),
                recipeDetailsDto.getImage(),
                recipeDetailsDto.getDescription(),
                recipeDetailsDto.getPricePerServing(),
                recipeDetailsDto.getSourceUrl(),
                recipeProducts);
    }

    public List<Recipe> convertAll(List<RecipeDetailsDto> recipesDto) {
        return recipesDto.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
