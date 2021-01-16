package com.cooksy.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class KrogerResult {

    @JsonSetter("meta")
    private Meta meta;

    @JsonSetter("data")
    private List<KrogerProduct> productsList;

    @Getter
    @Setter
    public static class Meta {

        @JsonSetter("pagination")
        private Pagination pagination;

        @Getter
        @Setter
        public static class Pagination {
            @JsonSetter("start")
            private Integer start;

            @JsonSetter("limit")
            private Integer limit;

            @JsonSetter("total")
            private Integer total;
        }
    }
}
