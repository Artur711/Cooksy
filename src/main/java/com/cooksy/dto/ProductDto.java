package com.cooksy.dto;

import com.cooksy.model.Recipe;
import lombok.*;

import java.util.List;

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
    private List<Recipe> recipes;

}
