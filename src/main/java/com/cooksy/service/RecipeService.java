package com.cooksy.service;

import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.RecipeDto;
import com.cooksy.model.Recipe;
import com.cooksy.repository.RecipeRepository;
import com.cooksy.util.converter.RecipeDetailsDtoToRecipeConverter;
import com.cooksy.util.converter.RecipeToRecipeDetailsDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeDetailsDtoToRecipeConverter recipeDetailsDtoToRecipeConverter;
    private final RecipeToRecipeDetailsDtoConverter recipeToRecipeDetailsDtoConverter;
    private final RecipeRepository recipeRepository;
    //private ProductService productService;

    public List<RecipeDetailsDto> getAll() {
        return recipeToRecipeDetailsDtoConverter.convertAll((List<Recipe>) recipeRepository.findAll());
    }

    public void saveRecipe(RecipeDetailsDto recipeDto) {
        recipeRepository.save(recipeDetailsDtoToRecipeConverter.convert(recipeDto));
    }

    public void deleteRecipe(RecipeDetailsDto recipeDto) {
        recipeRepository.delete(recipeDetailsDtoToRecipeConverter.convert(recipeDto));
    }

    public RecipeDetailsDto getRecipeById(Long recipeId) {

        if (recipeRepository.findById(recipeId).isPresent()) {
            return recipeToRecipeDetailsDtoConverter.convert(recipeRepository.findById(recipeId).get());
        }
        return null;
    }

    public void updateRecipe(Long recipeId, RecipeDetailsDto recipeDetailsDto){
        Recipe recipe = recipeDetailsDtoToRecipeConverter.convert(recipeDetailsDto);
        recipe.setRecipeId(recipeId);
        recipeRepository.save(recipe);
    }
}
