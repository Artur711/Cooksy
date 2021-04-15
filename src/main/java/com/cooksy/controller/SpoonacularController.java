package com.cooksy.controller;

import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.RecipesDto;
import com.cooksy.exception.ApiRequestException;
import com.cooksy.model.api.SpCuParameters;
import com.cooksy.service.RecipeService;
import com.cooksy.service.SpoonacularService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"http://localhost:4200", "https://cooksy-frontend.herokuapp.com"})
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipes")
public class SpoonacularController {

    private final SpoonacularService service;
    private final RecipeService recipeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    private RecipesDto getRecipes(@RequestParam(required = false) String start,
                                  @RequestParam(required = false) String ingredients,
                                  @RequestParam(required = false) String equipments,
                                  @RequestParam(required = false) String types) throws
            InterruptedException, IOException, URISyntaxException {
       try {
           SpCuParameters spCuParameters = new SpCuParameters(start, ingredients, equipments, types);
           return service.getRecipes(spCuParameters);
       }
       catch (NullPointerException e) {
           throw new ApiRequestException("API Spoonacular error");
       }
    }

    @GetMapping(value = "/recipe-detail/{id}", produces = APPLICATION_JSON_VALUE)
    public RecipeDetailsDto getRecipe(@PathVariable("id") String id) throws
            InterruptedException, IOException, URISyntaxException {
        try {
            RecipeDetailsDto recipeById = recipeService.getRecipeById(Long.valueOf(id));
            return (recipeById.equals(new RecipeDetailsDto())) ? service.getRecipeDetails(id) : recipeById;
        }
        catch (NullPointerException e) {
            throw new ApiRequestException("API Spoonacular error");
        }
    }
}
