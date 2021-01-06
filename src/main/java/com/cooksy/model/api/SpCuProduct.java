package com.cooksy.model.api;

import com.cooksy.model.ShpList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spCuProduct")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SpCuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSetter("id")
    private Long id;

    @JsonSetter("name")
    private String name;

    @Transient
    @JsonSetter("original")
    private String original;

    @Transient
    @JsonSetter("amount")
    private Integer amount;

    @Transient
    @JsonSetter("unit")
    private String unit;

    @Transient
    @JsonSetter("measures")
    private Measures measures;

    @ManyToMany
    private List<ShpList> shpList;

    public SpCuProduct(Long id, String name, String original, Integer amount, String unit, Measures measures) {
        this.id = id;
        this.name = name;
        this.original = original;
        this.amount = amount;
        this.unit = unit;
        this.measures = measures;
    }
}
