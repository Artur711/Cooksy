package com.cooksy.service;

import com.cooksy.dto.Id;
import com.cooksy.dto.ProductDto;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.model.Recipe;
import com.cooksy.repository.RecipeRepository;
import com.cooksy.util.converter.RecipeDetailsDtoToRecipeConverter;
import com.cooksy.util.converter.RecipeToRecipeDetailsDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RecipeService {

    private final RecipeDetailsDtoToRecipeConverter recipeDetailsDtoToRecipeConverter;
    private final RecipeToRecipeDetailsDtoConverter recipeToRecipeDetailsDtoConverter;
    private final RecipeRepository recipeRepository;
    private final ProductService productService;

    public List<RecipeDetailsDto> getAll() {
        List<RecipeDetailsDto> recipeDetailsDto = recipeToRecipeDetailsDtoConverter.convertAll((List<Recipe>) recipeRepository.findAll());
        log.info(String.format("Returned %d recipes", recipeDetailsDto.size()));
        return recipeDetailsDto;
    }

    public void saveRecipe(RecipeDetailsDto recipeDto) {
        recipeRepository.save(recipeDetailsDtoToRecipeConverter.convert(recipeDto));
        log.info(String.format("Saved recipe [id: %d]", recipeDto.getRecipeId()));
    }

    public void deleteRecipe(RecipeDetailsDto recipeDto) {
        recipeRepository.deleteRecipeCompositionByRecipeId(recipeDto.getRecipeId());
        recipeRepository.deleteRecipeByRecipeId(recipeDto.getRecipeId());
        List<RecipeDetailsDto> recipeDetailsDtoList = getAll();

        for (ProductDto productDto : recipeDto.getProducts()) {
            Optional<RecipeDetailsDto> any = recipeDetailsDtoList.stream()
                    .filter(recipe -> recipe.getProducts().contains(productDto))
                    .findAny();
            if (any.isEmpty()) {
                productService.deleteProduct(Id.idFromLong(productDto.getProductId()));
            }
        }
        log.info(String.format("Deleted recipe [id: %d]", recipeDto.getRecipeId()));
    }

    public RecipeDetailsDto getRecipeById(Long recipeId) {
        Optional<Recipe> recipeById = recipeRepository.findById(recipeId);
        if (recipeById.isPresent()) {
            RecipeDetailsDto detailsDto = recipeToRecipeDetailsDtoConverter.convert(recipeById.get());
            log.info(String.format("Returned recipe by [id: %d]", recipeId));
            return detailsDto;
        }
        log.info("Returned empty recipe");
        return new RecipeDetailsDto();
    }

    public void updateRecipe(Long recipeId, RecipeDetailsDto recipeDetailsDto){
        Recipe recipe = recipeDetailsDtoToRecipeConverter.convert(recipeDetailsDto);
        recipe.setRecipeId(recipeId);
        recipeRepository.save(recipe);
        log.info(String.format("Updated recipe [id: %d]", recipeId));
    }
}
