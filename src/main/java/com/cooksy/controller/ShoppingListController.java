package com.cooksy.controller;

import com.cooksy.dto.ProductDto;
import com.cooksy.exception.NotFoundException;
import com.cooksy.model.Product;
import com.cooksy.dto.RequestProductsDto;
import com.cooksy.model.User;
import com.cooksy.service.ProductService;
import com.cooksy.service.ShoppingListService;
import com.cooksy.service.ShoppingProductService;
import com.cooksy.util.JwtUtils;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import com.cooksy.util.converter.ShoppingProductToProductDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/shopping-list")
public class ShoppingListController {

    private final ProductDtoToProductConverter productDtoToProductConverter;
    private final ProductService productService;
    private final JwtUtils jwtUtils;
    private final ShoppingListService shoppingListService;
    private final ShoppingProductService shoppingProductService;
    private final ShoppingProductToProductDtoConverter shoppingProductToProductDtoConverter;


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<List<ProductDto>> getProductsByUserId() {
//        @RequestHeader("Authorization") String headerValue  // token rout
//        String userIdFromToken = getUserIdFromToken(headerValue);
        User user = new User();
        user.setUserId(1L);  // we need to assign user id from token

        List<List<ProductDto>> userLists = shoppingProductToProductDtoConverter.convertAll(shoppingProductService.getUserLists(user));
        return userLists;

    }


    @PostMapping(value = "/add-to-list", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRecipeToShoppingList(@RequestBody RequestProductsDto requestProductsDto) {

//        @RequestHeader("Authorization") String headerValue
//        String userIdFromToken = getUserIdFromToken(headerValue);    // with token rout

        User user = new User();

//        user.setUserId(Id.idFromString(jwtUtils.getUsernameIDFromJwtToken(userIdFromToken)).getValue());

        user.setUserId(1L);
        List<Product> products = productDtoToProductConverter.convertAll(Arrays.asList(requestProductsDto.getProductDtos()));
        productService.addOnlyNewProducts(products);

        shoppingProductService.saveProductsToShoppingList(products, user, requestProductsDto.getDate());
    }

    private String getUserIdFromToken(String tokenValue) {
        Optional<String> tokenFromHeader = jwtUtils.getTokenFromHeader(tokenValue);
        String valueOfToken = "";
        if (tokenFromHeader.isPresent()) {
            valueOfToken = tokenFromHeader.get();
        }
        String usernameIDFromJwtToken;
        try {
            usernameIDFromJwtToken = jwtUtils.getUsernameIDFromJwtToken(valueOfToken);
        } catch (RuntimeException e) {
            throw new NotFoundException("User");
        }
        return usernameIDFromJwtToken;
    }
}
