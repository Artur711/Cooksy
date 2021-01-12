package com.cooksy.util.converter.api;

import com.cooksy.dto.ProductDto;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.model.api.SpCuRecipeDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SpCuRecipeDetailsToRecipeDetailsDtoConverter {

    private final SpCuProductToProductDtoConverter spCuProductToProductDtoConverter;

    public RecipeDetailsDto convert(SpCuRecipeDetails recipeDetails) {
        List<ProductDto> productsDto = spCuProductToProductDtoConverter.convertAll(recipeDetails.getProductList());
        String description = (recipeDetails.getDescription() != null) ? recipeDetails.getDescription() : recipeDetails.getSummary();

        return new RecipeDetailsDto(recipeDetails.getId(),
                recipeDetails.getTitle(),
                recipeDetails.getImage(),
                clearString(description),
                recipeDetails.getPrice()/100,
                recipeDetails.getSourceUrl(),
                productsDto);
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
        str = str.replace("<p", "");
        str = str.replace("</p>", "");
        str = str.replace(">", " ");
        str = str.replace("\"", "");
        return str;
    }
}
