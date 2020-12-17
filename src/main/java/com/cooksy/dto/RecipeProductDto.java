package com.cooksy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RecipeProductDto {
    private Long rpId;
    private ProductDto product;
    private Double quantity;
    private String strPriceByQuantity;

    public RecipeProductDto(Long rpId, ProductDto product, Double quantity) {
        this.rpId = rpId;
        this.product = product;
        this.quantity = quantity;
        this.strPriceByQuantity = getStrPriceByQuantity(product.getPrice(), quantity);
    }

    private String getStrPriceByQuantity(Double price, Double quantity) {
        return String.format("%.2f", price * quantity);
    }
}
