package com.cooksy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDto {
    private Long shoppingListId;
    private String isConfirmed;
    private Long userId;
    private String name;
    private Date date;
    private List<ProductDto>  products;

}
