package com.cooksy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;
    private Boolean isChecked;
    private String name;
    private String original;
    private Integer amount;
    private String unit;
    private Double measuresAmount;
    private String measuresUnitShort;
    private Long shoppingListId;
    private String date;


    public ProductDto(Long productId, Boolean isChecked, String name, String original, Integer amount, String unit, Double measuresAmount, String measuresUnitShort) {
        this.productId = productId;
        this.isChecked = isChecked;
        this.name = name;
        this.original = original;
        this.amount = amount;
        this.unit = unit;
        this.measuresAmount = measuresAmount;
        this.measuresUnitShort = measuresUnitShort;
    }
}
