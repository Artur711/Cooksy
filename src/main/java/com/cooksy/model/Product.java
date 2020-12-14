package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productID;

    private String name;

    private Double price;

    @Column(name = "product_type_id")
    private Long productTypeID;

    @Column(name = "market_id")
    private Long marketID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "grammage_id")
    private Grammage grammage;

    public Product(String name, Double price, Long productTypeID, Long marketID, Grammage grammage) {
        this.name = name;
        this.price = price;
        this.productTypeID = productTypeID;
        this.marketID = marketID;
        this.grammage = grammage;
    }
}


