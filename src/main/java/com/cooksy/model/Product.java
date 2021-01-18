package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @Column(name = "product_id")
    private Long productID;
    private String name;
    private String original;
    private Integer amount;
    private String unit;
    private Double measuresAmount;
    private String measuresUnitShort;
}


