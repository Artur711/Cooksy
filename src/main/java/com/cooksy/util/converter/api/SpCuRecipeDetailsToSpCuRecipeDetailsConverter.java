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
        String description = (recipeDetails.getDescription() != null) ? recipeDetails.getDescription() : recipeDetails.getSummary();

        return new SpCuRecipeDetailsDto(recipeDetails.getId(),
                recipeDetails.getTitle(),
                recipeDetails.getImage(),
                clearString(description),
                recipeDetails.getPrice()/100,
                recipeDetails.getSourceUrl(),
                spCuProductDtos);
    }

    private String clearString(String str){
        str = str.replace("<ol>", "");
        str = str.replace("</ol>", "");
        str = str.replace("<li>", "");
        str = str.replace("</li>", "");
        str = str.replace("<span>", "");
        str = str.replace("</span>", "");
        str = str.replace("<b>", "");
        str = str.replace("</b>", "");
        str = str.replace("<a href=", " ");
        str = str.replace("</a>", "");
        str = str.replace(">", " ");
        return str;
    }
}
