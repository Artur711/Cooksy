package com.cooksy.util.converter;

import com.cooksy.dto.ProductDto;
import com.cooksy.dto.RecipeProductDto;
import com.cooksy.model.RecipeProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@AllArgsConstructor
public class RecipeProductToRecipeProductDtoConverter {
    private final ProductToProductDtoConverter productToProductDtoConverter;

    public RecipeProductDto convert(RecipeProduct recipeProduct){
        ProductDto productDto = productToProductDtoConverter.convert(recipeProduct.getProduct());
        return new RecipeProductDto(recipeProduct.getRpId(),
                productDto,
                recipeProduct.getQuantity());
    }

    public List<RecipeProductDto> convertAll(List<RecipeProduct> recipeProducts){
        return recipeProducts.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
