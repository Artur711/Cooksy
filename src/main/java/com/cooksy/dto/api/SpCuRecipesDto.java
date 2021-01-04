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

    private Integer totalResults;

    private Integer offset;

    private List<SpCuRecipeDto> recipes;
}
