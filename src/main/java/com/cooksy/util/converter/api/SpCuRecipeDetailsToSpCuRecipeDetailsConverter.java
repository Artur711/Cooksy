package com.cooksy.util.converter.api;

import com.cooksy.dto.api.SpCuProductDto;
import com.cooksy.dto.api.SpCuRecipeDetailsDto;
import com.cooksy.model.api.SpCuRecipeDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SpCuRecipeDetailsToSpCuRecipeDetailsConverter {

    private SpCuProductToSpCuProductDtoConverter spCuProductToSpCuProductDtoConverter;

    public SpCuRecipeDetailsDto convert(SpCuRecipeDetails recipeDetails) {
        List<SpCuProductDto> spCuProductDtos = spCuProductToSpCuProductDtoConverter.convertAll(recipeDetails.getProductList());
        return new SpCuRecipeDetailsDto(recipeDetails.getId(),
                recipeDetails.getTitle(),
                recipeDetails.getDescription(),
                recipeDetails.getPrice()/100,
                recipeDetails.getSourceUrl(),
                spCuProductDtos);
    }
}
