package com.cooksy.util.converter.api;

import com.cooksy.dto.RecipeDto;
import com.cooksy.dto.RecipesDto;
import com.cooksy.model.api.SpCuRecipes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SpCuRecipesToRecipesDtoConverter {

    private final SpCuRecipeToRecipeDtoConverter spCuRecipeToRecipeDtoConverter;

    public RecipesDto convert(SpCuRecipes recipes) {
        List<RecipeDto> spCuRecipeDtos = spCuRecipeToRecipeDtoConverter.convertAll(recipes.getSpCuRecipes());

        return new RecipesDto(recipes.getLimit(),
                round(recipes.getTotalResults(), recipes.getLimit()),
                round(recipes.getOffset() + 1, recipes.getLimit()),
                spCuRecipeDtos);
    }

    private Integer round(Integer divisor, Integer dividend) {
        return (divisor % dividend == 0) ? (divisor / dividend) : (divisor / dividend + 1);
    }
}
