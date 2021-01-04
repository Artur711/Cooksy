package com.cooksy.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Recipe {

    @JsonSetter("id")
    private Long id;

    @JsonSetter("title")
    private String name;

    @JsonSetter("image")
    private String imageUrl;
}
