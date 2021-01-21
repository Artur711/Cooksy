package com.cooksy.controller;

import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.RecipesDto;
import com.cooksy.service.RecipeService;
import com.cooksy.service.SpoonacularService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipes")
public class SpoonacularController {

    private final SpoonacularService service;
    private final RecipeService recipeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    private RecipesDto getRecipes(@RequestParam(required = false) String page,
                                  @RequestParam(required = false) String ingredients,
                                  @RequestParam(required = false) String equipments,
                                  @RequestParam(required = false) String types) throws
            InterruptedException, IOException, URISyntaxException {

        return service.getRecipes(page, ingredients, equipments, types);
    }

    @GetMapping(value = "/vegetarian", produces = APPLICATION_JSON_VALUE)
    public RecipesDto getRecipesVegetarian() throws InterruptedException, IOException, URISyntaxException {
        return service.getRecipesVegetarian();
    }

    @GetMapping(value = "/recipe-detail/{id}", produces = APPLICATION_JSON_VALUE)
    public RecipeDetailsDto getRecipe(@PathVariable("id") String id) throws
            InterruptedException, IOException, URISyntaxException {
        RecipeDetailsDto recipeById = recipeService.getRecipeById(Long.valueOf(id));
        return (recipeById.equals(new RecipeDetailsDto())) ? service.getRecipeDetails(id) : recipeById;
    }
}
