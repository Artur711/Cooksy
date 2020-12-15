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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;
    private String name;

    @ManyToMany
    @JoinTable(name = "recipe_composition", joinColumns = @JoinColumn(name = "Recipe_ID"), inverseJoinColumns = @JoinColumn(name = "recipe_product_ID"))
    private List<RecipeProduct> recipeProducts;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String photoUrl;
    private String author;

}
