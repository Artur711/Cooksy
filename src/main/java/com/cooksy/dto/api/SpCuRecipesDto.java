package com.cooksy.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpCuRecipesDto {

    private Integer limit;

    private Integer numberOfPages;

    private Integer page;

    private List<SpCuRecipeDto> recipes;
}
