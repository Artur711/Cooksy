package com.cooksy.util.converter;

import com.cooksy.dto.RecipeProductDto;
import com.cooksy.model.Product;
import com.cooksy.model.RecipeProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class RecipeProductDtoToRecipeProductConverter {

    public RecipeProduct convert(RecipeProductDto recipeProductDto) {
        Product product = new Product();
        product.setProductID(recipeProductDto.getProductDto().getProductID());
        return new RecipeProduct(recipeProductDto.getRpId(),
                product,
                recipeProductDto.getQuantity());
    }

    public List<RecipeProduct> convertAll(List<RecipeProductDto> recipeProductDtos) {
        return recipeProductDtos.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
