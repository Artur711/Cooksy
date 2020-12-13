package com.cooksy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipeProductDto {
    private Long rpId;
    private ProductDto productDto;
    private Double quantity;
}
