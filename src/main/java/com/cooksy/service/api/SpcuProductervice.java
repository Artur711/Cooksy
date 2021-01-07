package com.cooksy.service.api;

import com.cooksy.dto.api.SpCuRecipeDetailsDto;
import com.cooksy.model.api.SpCuProduct;
import com.cooksy.repository.api.SpcuProductRepository;
import com.cooksy.util.converter.api.SpCuProductDtoToSpCuProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpcuProductervice {

    private final SpcuProductRepository spcuProductRepository;
    private final SpCuProductDtoToSpCuProductConverter spCuProductDtoToSpCuProductConverter;

    public void addSpCuProduct(List<SpCuProduct> spCuProducts) {
        spCuProducts.stream().filter(product -> !spcuProductRepository.existsById(product.getId()))
                .forEach(spcuProductRepository::save);
    }

    public List<SpCuProduct> getProductsFromRecipe(SpCuRecipeDetailsDto recipe) {
        ArrayList<SpCuProduct> spCuProducts = new ArrayList<>();
        return recipe.getProducts()
                .stream()
                .map(spCuProductDtoToSpCuProductConverter::convert)
                .collect(Collectors.toList());

    }
}
