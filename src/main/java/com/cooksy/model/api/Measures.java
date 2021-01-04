package com.cooksy.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Measures {

    @JsonSetter("metric")
    private Metric metric;

    @Getter
    @Setter
    public static class Metric {

        @JsonSetter("amount")
        private String amount;

        @JsonSetter("unitShort")
        private String unitShort;

        @JsonSetter("unitLong")
        private String unitLong;
    }
}
