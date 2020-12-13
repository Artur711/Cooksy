package com.cooksy.dto;


import com.cooksy.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private Long favoriteId;
    private UserDto user;
    private RecipeDto recipe;
}
