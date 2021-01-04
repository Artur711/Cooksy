package com.cooksy.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpCuRecipeDetailsDto {

    private Long recipeId;

    private String tittle;

    private String description;

    private Double pricePerServing;

    private String sourceUrl;

    private List<SpCuProductDto> products;
}
