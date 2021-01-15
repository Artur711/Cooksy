package com.cooksy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {

    private Long favoriteId;
    private UserDto user;
    private RecipeDetailsDto recipe;
}
