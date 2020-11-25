package com.cooksy.dto;

import lombok.*;

//@Setter
//@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Long productID;
    private String name;
    private Double price;
    private Long productTypeID;
    private Long marketID;
    private Long gmID;

}
