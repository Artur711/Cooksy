package com.cooksy.backdoor;

import com.cooksy.dto.RecipeDto;
import com.cooksy.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v3/recipe")
public class RecipeBackDoor {

    private final RecipeService recipeService;

    @GetMapping
    public List<RecipeDto> getAll(){
        return recipeService.getAll();
    }

    @PostMapping
    public void saveRecipe(@RequestBody RecipeDto recipeDto){
        recipeService.saveRecipe(recipeDto);
    }
}


