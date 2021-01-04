package com.cooksy.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpCuRecipeDto {

    private Long id;

    private String name;

    private String imageUrl;
}
