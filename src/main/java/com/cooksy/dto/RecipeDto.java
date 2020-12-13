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
    private List<Product> products;
    private String description;
    private String photoUrl;
    private String author;
}
