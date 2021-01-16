package com.cooksy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KrogerProductDto {

    private String strProductId;
    private String description;
    private Double regularPrice;
    private Double promoPrice;
    private String size;
    private String url;
}
