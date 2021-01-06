package com.cooksy.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SpCuRecipe {

    @JsonSetter("id")
    private Long id;

    @JsonSetter("title")
    private String title;

    @JsonSetter("image")
    private String imageUrl;
}
