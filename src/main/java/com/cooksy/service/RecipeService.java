package com.cooksy.service;

import com.cooksy.dto.RecipeDto;
import com.cooksy.model.Recipe;
import com.cooksy.repository.RecipeRepository;
import com.cooksy.util.converter.RecipeDtoToRecipeConverter;
import com.cooksy.util.converter.RecipeToRecipeDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeDtoToRecipeConverter recipeDtoToRecipeConverter;
    private final RecipeToRecipeDtoConverter recipeToRecipeDtoConverter;
    private RecipeRepository recipeRepository;
    //private ProductService productService;

    public List<RecipeDto> getAll() {return recipeToRecipeDtoConverter.convertAll((List<Recipe>) recipeRepository.findAll()); }

    public void saveRecipe(RecipeDto recipeDto) {
        recipeRepository.save(recipeDtoToRecipeConverter.convert(recipeDto));
    }

    public void deleteRecipe(RecipeDto recipeDto) {
        recipeRepository.delete(recipeDtoToRecipeConverter.convert(recipeDto));
    }

    public RecipeDto getRecipeById(Long recipeId) {

        if (recipeRepository.findById(recipeId).isPresent()) {
            return recipeToRecipeDtoConverter.convert(recipeRepository.findById(recipeId).get());
        }
        return null;
    }

    public void updateRecipe(Long recipeId, RecipeDto recipeDto){
        Recipe recipe = recipeDtoToRecipeConverter.convert(recipeDto);
        recipe.setRecipeId(recipeId);
        recipeRepository.save(recipe);
    }
}
