package com.cooksy.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MeasuresDto {

    @JsonSetter("metric")
    private MetricDto metricDto;

    @Getter
    @Setter
    public static class MetricDto {

        @JsonSetter("amount")
        private String amount;

        @JsonSetter("unitShort")
        private String unitShort;

        @JsonSetter("unitLong")
        private String unitLong;
    }
}
