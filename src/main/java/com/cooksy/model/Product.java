package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productID;

    private String name;

    private Double price;

    @Column(name = "product_type_id")
    private Long productTypeID;

    @Column(name = "market_id")
    private Long marketID;

    @Column(name = "gm_id")
    private Long gmID;

    @ManyToMany
    @JoinTable(name = "products_of_recipes", joinColumns = @JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns = @JoinColumn(name = "RECIPE_ID"))
    private List<Recipe> recipes;
}
