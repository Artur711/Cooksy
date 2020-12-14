package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Grammage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grammage_id")
    private Long gmId;

    private Double quantity;
    private String grammage;

    public Grammage(Double quantity, String grammage) {
        this.quantity = quantity;
        this.grammage = grammage;
    }
}

