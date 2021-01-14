package com.cooksy.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;
    private String name;
    private String original;
    private Integer amount;
    private String unit;
    private Double measuresAmount;
    private String measuresUnitShort;
}
