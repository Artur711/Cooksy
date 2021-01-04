package com.cooksy.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpCuProductDto {

    private Long productId;

    private String name;

    private String original;

    private Integer amount;

    private String unit;

    private String measuresAmount;

    private String measuresUnitShort;
}
