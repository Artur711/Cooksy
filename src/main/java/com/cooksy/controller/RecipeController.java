package com.cooksy.controller;

import com.cooksy.dto.RecipeDto;
import com.cooksy.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping
    public String getRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAll());
        return "menu";
    }

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable("id") Long id, Model model) {
        RecipeDto recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }
}
