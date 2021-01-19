package com.cooksy.service.api;

import com.cooksy.dto.RecipeDetailsDto;
import com.cooksy.model.Product;
import com.cooksy.repository.ProductRepository;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpcuProductervice {

    private final ProductRepository productRepository;
    private final ProductDtoToProductConverter spCuProductDtoToSpCuProductConverter;



    public List<Product> getProductsFromRecipe(RecipeDetailsDto recipe) {
        ArrayList<Product> products = new ArrayList<>();
        return recipe.getProducts()
                .stream()
                .map(spCuProductDtoToSpCuProductConverter::convert)
                .collect(Collectors.toList());

    }
}
