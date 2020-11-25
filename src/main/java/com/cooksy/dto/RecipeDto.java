package com.cooksy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private Long recipeId;
    private Long productID;
    private String description;
    private String photoUrl;
    private String author;
}
