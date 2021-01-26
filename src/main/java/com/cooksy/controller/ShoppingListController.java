package com.cooksy.controller;

import com.cooksy.dto.Id;
import com.cooksy.dto.ProductDto;
import com.cooksy.exception.NotFoundException;
import com.cooksy.model.Product;
import com.cooksy.model.ShpList;
import com.cooksy.model.User;
import com.cooksy.service.ProductService;
import com.cooksy.service.ShoppingListService;
import com.cooksy.util.JwtUtils;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

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


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> getProductsByUserId(@RequestHeader("Authorization") String headerValue) {
        String userIdFromToken = getUserIdFromToken(headerValue);
        Set<ProductDto> uniqueProducts = new HashSet<>();
        List<ProductDto> usersProductDtosList = shoppingListService.getAll().stream()
                .filter(shoppingListDto -> shoppingListDto.getUser().getUserId() == Long.parseLong(userIdFromToken))
                .flatMap(shoppingListDto -> shoppingListDto.getProducts().stream())
                .collect(Collectors.toList());
        usersProductDtosList.forEach(productDto -> {
            double counter = 1;
            for (ProductDto theProduct : usersProductDtosList
            ) {
                if (theProduct.getProductId().equals(productDto.getProductId())) {
                    counter += 1;
                }
            }
            productDto.setMeasuresAmount(productDto.getMeasuresAmount() * counter);
            uniqueProducts.add(productDto);

        });
        return new ArrayList<>(uniqueProducts);
    }


    @PostMapping(value = "/add-to-list", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRecipeToShoppingList(@RequestBody ProductDto[] productDTOs, @RequestHeader("Authorization") String headerValue) {
        String userIdFromToken = getUserIdFromToken(headerValue);
        User user = new User();
        user.setUserId(Id.idFromString(jwtUtils.getUsernameIDFromJwtToken(userIdFromToken)).getValue());
        productService.addOnlyNewProducts(productDtoToProductConverter.convertAll(Arrays.asList(productDTOs)));
        List<Product> userProductsToShoppingList = productDtoToProductConverter.convertAll(Arrays.stream(productDTOs)
                .filter(ProductDto::getIsChecked)
                .collect(Collectors.toList()));
        shoppingListService.saveUsersShoppingList(new ShpList(userProductsToShoppingList, user));
    }

    private String getUserIdFromToken(String tokenValue){
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
