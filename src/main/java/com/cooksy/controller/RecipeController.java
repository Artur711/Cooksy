package com.cooksy.controller;

import com.cooksy.dto.Id;
import com.cooksy.dto.RecipeDto;
import com.cooksy.dto.api.SpCuRecipeDetailsDto;
import com.cooksy.model.ShpList;
import com.cooksy.model.User;
import com.cooksy.model.api.SpCuProduct;
import com.cooksy.service.RecipeService;
import com.cooksy.service.ShoppingListService;
import com.cooksy.service.UserService;
import com.cooksy.service.api.SpcuProductervice;
import com.cooksy.util.converter.api.SpCuProductDtoToSpCuProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;
    private final SpCuProductDtoToSpCuProductConverter spCuProductDtoToSpCuProductConverter;
    private final SpcuProductervice spcuProductervice;
    private final SpoonacularController spoonacularController;
    private final ShoppingListService shoppingListService;

    @GetMapping
    public List<RecipeDto> getAll() {
        return recipeService.getAll();
    }

    @GetMapping("/{id}")
    public RecipeDto getById(@PathVariable String id) {
        return recipeService.getRecipeById((Long.parseLong(id)));
    }

    @GetMapping("/{recipeID}/{userID}")
    public void addRecipeToShoppingList(@PathVariable String recipeID, @PathVariable String userID) {
        SpCuRecipeDetailsDto theSpoonRecipe = spoonacularController.getRecipe(recipeID);
        User user = new User();
        user.setUserId(Id.idFromString(userID).getValue());
        List<SpCuProduct> productsFromRecipe = spcuProductervice.getProductsFromRecipe(theSpoonRecipe);
        spcuProductervice.addSpCuProduct(productsFromRecipe);
        shoppingListService.saveUsersShoppingList(new ShpList(productsFromRecipe,user));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.saveRecipe(recipeDto);
    }

    @PutMapping("/{id}")
    public void updateRecipe(@PathVariable String id, @RequestBody RecipeDto recipeDto) {
        recipeService.updateRecipe((Long.valueOf(id)), recipeDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.deleteRecipe(recipeDto);
    }
}
