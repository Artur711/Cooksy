package com.cooksy.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class KrogerProduct {

    @JsonSetter("productId")
    private String strProductId;

    @JsonSetter("description")
    private String description;

    @JsonSetter("images")
    private List<KrogerImage> krogerImageList;

    @JsonSetter("items")
    private List<KrogerItem> krogerItems;
}
