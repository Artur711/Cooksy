package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    private Long productID;
    private String name;
    private String original;
    private Integer amount;
    private String unit;
    private String measuresAmount;
    private String measuresUnitShort;

//    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
//    private List<Recipe> recipes = new ArrayList<>();
//
//    @ManyToMany
//            (mappedBy = "spCuProducts", cascade = CascadeType.ALL)
//    private List<ShpList> shpList;
//
//    public Product(Long productID, String name, String original, Integer amount,
//                   String unit, String measuresAmount, String measuresUnitShort) {
//        this.productID = productID;
//        this.name = name;
//        this.original = original;
//        this.amount = amount;
//        this.unit = unit;
//        this.measuresAmount = measuresAmount;
//        this.measuresUnitShort = measuresUnitShort;
//    }
}


