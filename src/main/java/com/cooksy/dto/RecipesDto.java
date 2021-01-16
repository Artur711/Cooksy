package com.cooksy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipesDto {

    private Integer limit;
    private Integer numberOfPages;
    private Integer page;
    private List<RecipeDto> recipes;
}
