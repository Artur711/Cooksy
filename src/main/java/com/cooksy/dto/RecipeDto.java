package com.cooksy.dto;

import com.cooksy.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private Long recipeId;
    private String name;
    private List<RecipeProductDto> recipeProducts;
    private String description;
    private String photoUrl;
    private String author;
}
