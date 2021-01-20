package com.cooksy.dto;


import com.cooksy.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDto {
    private Long shpListId;
    private String isConfirmed;
    private User user;
    private String name;
    private Date date;
    private List<ProductDto>  products;

}
