package com.cooksy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private Long id;
    private String name;
    private String imageUrl;
}
