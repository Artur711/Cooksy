package com.cooksy.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SpCuRecipeDetails {

    @JsonSetter("id")
    private Long id;

    @JsonSetter("title")
    private String title;

    @JsonSetter("instructions")
    private String description;

    @JsonSetter("pricePerServing")
    private Double price;

    @JsonSetter("sourceUrl")
    private String sourceUrl;

    @JsonSetter("extendedIngredients")
    private List<SpCuProduct> productList;

//    @JsonSetter("dishTypes")
//    private List<String> dish;
}
