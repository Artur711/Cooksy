package com.cooksy.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class RecipeDetails {

    @JsonSetter("id")
    private Long id;

    @JsonSetter("title")
    private String name;

    @JsonSetter("instructions")
    private String description;

    @JsonSetter("pricePerServing")
    private Long price;

    @JsonSetter("sourceUrl")
    private String sourceUrl;

    @JsonSetter("extendedIngredients")
    private List<Product> productList;

    @JsonSetter("dishTypes")
    private List<String> dish;
}
