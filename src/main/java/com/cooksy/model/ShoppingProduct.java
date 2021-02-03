package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping_Product")
public class ShoppingProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private User user;

    @OneToOne
    private Product product;

    @OneToOne
    private ShpList shpList;

    private Double amount;

    public ShoppingProduct(User user, Product product, ShpList shpList, Double amount) {
        this.user = user;
        this.product = product;
        this.shpList = shpList;
        this.amount = amount;
    }
}
