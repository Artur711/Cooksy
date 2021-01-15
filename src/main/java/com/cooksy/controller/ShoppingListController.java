package com.cooksy.controller;

import com.cooksy.dto.Id;
import com.cooksy.dto.ProductDto;
import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.dto.ShoppingListDto;
import com.cooksy.model.Product;
import com.cooksy.model.ShpList;
import com.cooksy.model.User;
import com.cooksy.service.RecipeService;
import com.cooksy.service.ShoppingListService;
import com.cooksy.service.UserService;
import com.cooksy.service.api.SpcuProductervice;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private final RecipeService recipeService;
    private final UserService userService;
    private final ProductDtoToProductConverter productDtoToProductConverter;
    private final SpcuProductervice spcuProductervice;
    private final SpoonacularController spoonacularController;
    private final ShoppingListService shoppingListService;

//    @GetMapping
//    public List<RecipeDetailsDto> getAll() {
//        return recipeService.getAll();
//    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> getByUserId(@PathVariable String id) {
        return  shoppingListService.getAll().stream()
                .filter(shoppingListDto -> shoppingListDto.getUser().getUserId() == Long.parseLong(id))
                .flatMap(shoppingListDto -> shoppingListDto.getProducts().stream())
                .collect(Collectors.toList());
    }

    @PostMapping("/{recipeID}/{userID}")
    public void addRecipeToShoppingList(@PathVariable String recipeID, @PathVariable String userID) {
        RecipeDetailsDto recipeDetailsDto = spoonacularController.getRecipe(recipeID);
        User user = new User();
        user.setUserId(Id.idFromString(userID).getValue());
        List<Product> productsFromRecipe = productDtoToProductConverter.convertAll(recipeDetailsDto.getProducts());
        spcuProductervice.addSpCuProduct(productsFromRecipe);
        shoppingListService.saveUsersShoppingList(new ShpList(productsFromRecipe,user));
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void saveRecipe(@RequestBody RecipeDetailsDto recipeDetailsDto) {
//        recipeService.saveRecipe(recipeDetailsDto);
//    }

//    @PutMapping("/{id}")
//    public void updateRecipe(@PathVariable String id, @RequestBody RecipeDetailsDto recipeDetailsDto) {
//        recipeService.updateRecipe((Long.valueOf(id)), recipeDetailsDto);
//    }

//    @DeleteMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteRecipe(@RequestBody RecipeDetailsDto recipeDetailsDto) {
//        recipeService.deleteRecipe(recipeDetailsDto);
//    }
}
