package com.cooksy.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class KrogerItem {

    @JsonSetter("nationalPrice")
    private Price price;

    @JsonSetter("size")
    private String size;

    @Getter
    @Setter
    public static class Price {

        @JsonSetter("regular")
        private Double regularPrice;

        @JsonSetter("promo")
        private  Double promoPrice;
    }
}
