package com.cooksy.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Product {

    @JsonSetter("id")
    private Long id;

    @JsonSetter("name")
    private String name;

    @JsonSetter("original")
    private String original;

    @JsonSetter("amount")
    private Integer amount;

    @JsonSetter("unit")
    private String unit;

    @JsonSetter("measures")
    private MeasuresDto measures;
}
