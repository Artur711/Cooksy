package com.cooksy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecipeDto {

    private Long recipeId;
    private String name;
    private List<RecipeProductDto> recipeProducts;
    private String description;
    private String photoUrl;
    private String author;
    private Double sumPrices;

    public RecipeDto(Long recipeId, String name, List<RecipeProductDto> recipeProducts, String description, String photoUrl, String author) {
        this.recipeId = recipeId;
        this.name = name;
        this.recipeProducts = recipeProducts;
        this.description = description;
        this.photoUrl = photoUrl;
        this.author = author;
        this.sumPrices = getSumProductsPrices(recipeProducts);
    }

    private Double getSumProductsPrices(List<RecipeProductDto> recipeProducts) {
        return recipeProducts.stream()
                .mapToDouble(recipeProduct -> recipeProduct.getQuantity() * recipeProduct.getProduct().getPrice())
                .sum();
    }
}
