package com.cooksy.util.converter;

import com.cooksy.dto.ProductDto;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecipeToRecipeDetailsDtoConverter {
    private final ProductToProductDtoConverter productToProductDtoConverter;

    public RecipeDetailsDto convert(Recipe recipe) {
        List<ProductDto> productsDto = productToProductDtoConverter.convertAll(recipe.getProducts());
        return new RecipeDetailsDto(recipe.getRecipeId(),
                recipe.getTittle(),
                recipe.getImage(),
                recipe.getDescription(),
                recipe.getPricePerServing(),
                recipe.getSourceUrl(),
                productsDto);
    }

    public List<RecipeDetailsDto> convertAll(List<Recipe> recipes) {
        return recipes.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
