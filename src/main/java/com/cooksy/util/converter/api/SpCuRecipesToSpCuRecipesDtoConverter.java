package com.cooksy.util.converter.api;

import com.cooksy.dto.api.SpCuRecipeDto;
import com.cooksy.dto.api.SpCuRecipesDto;
import com.cooksy.model.api.SpCuRecipes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SpCuRecipesToSpCuRecipesDtoConverter {

    private SpCuRecipeToSpCuRecipeDtoConverter spCuRecipeToSpCuRecipeDtoConverter;

    public SpCuRecipesDto convert(SpCuRecipes recipes) {
        List<SpCuRecipeDto> spCuRecipeDtos = spCuRecipeToSpCuRecipeDtoConverter.convertAll(recipes.getSpCuRecipes());

        return new SpCuRecipesDto(recipes.getLimit(),
                recipes.getTotalResults(),
                recipes.getOffset(),
                spCuRecipeDtos);
    }
}
