package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")

public class Recipe {

    @Id
    private Long recipeId;
    private String tittle;
    private String image;

    @Column(columnDefinition="TEXT")
    private String description;

    private Double pricePerServing;
    private String sourceUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_composition", joinColumns = @JoinColumn(name = "Recipe_ID"),
            inverseJoinColumns = @JoinColumn(name = "recipe_product_ID"))
    private List<Product> products;
}
