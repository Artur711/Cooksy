package com.cooksy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDetailsDto {

    private Long recipeId;
    private String tittle;
    private String image;
    private String description;
    private Double pricePerServing;
    private String sourceUrl;
    private Integer readyInMinutes;
    private List<ProductDto> products;
}
