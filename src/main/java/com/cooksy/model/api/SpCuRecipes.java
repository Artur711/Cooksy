package com.cooksy.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SpCuRecipes {

    @JsonSetter("number")
    private Integer limit;

    @JsonSetter("totalResults")
    private Integer totalResults;

    @JsonSetter("offset")
    private Integer offset;

    @JsonSetter("results")
    private List<SpCuRecipe> spCuRecipes;
}
