package com.cooksy.util.converter.api;

import com.cooksy.dto.ProductDto;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.model.api.SpCuRecipeDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class SpCuRecipeDetailsToRecipeDetailsDtoConverter {

    private final SpCuProductToProductDtoConverter spCuProductToProductDtoConverter;

    public RecipeDetailsDto convert(SpCuRecipeDetails recipeDetails) {
        List<ProductDto> productsDto = spCuProductToProductDtoConverter.convertAll(recipeDetails.getProductList());
        String description = (recipeDetails.getDescription() != null) ? recipeDetails.getDescription() : recipeDetails.getSummary();
        productsDto = removeRepeats(productsDto);

        return new RecipeDetailsDto(recipeDetails.getId(),
                recipeDetails.getTitle(),
                recipeDetails.getImage(),
                clearString(description),
                recipeDetails.getPrice()/100,
                recipeDetails.getSourceUrl(),
                recipeDetails.getReadyInMinutes(),
                productsDto);
    }

    private String clearString(String str){
        String tempStr = str;
        List<String> replaces = List.of("<ol>", "</ol>", "<li>", "</li>", "<span>", "</span>",
                "<b>", "</b>", "<a href=", "</a>", "<p", "</p>", ">", "\"", "'");

        for (String replace : replaces) {
            tempStr = tempStr.replace(replace, "");
        }
        return tempStr;
    }

    private List<ProductDto> removeRepeats(List<ProductDto> productsDto) {
        List<ProductDto> tempList = new LinkedList<>();

        for (ProductDto product : productsDto) {
            int index = getIndexProductFromList(tempList, product);
            if (index == -1) {
                tempList.add(product);
            }
            else {
                ProductDto productDto = tempList.get(index);
                productDto.setMeasuresAmount(productDto.getMeasuresAmount() + product.getMeasuresAmount());
            }
        }
        return tempList;
    }

    private int getIndexProductFromList(List<ProductDto> tempList, ProductDto productDto) {
        for (int i = 0; i < tempList.size(); i++) {
            if (productDto.getProductId().equals(tempList.get(i).getProductId())) {
                return i;
            }
        }
        return -1;
    }
}
