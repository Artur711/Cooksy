package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_list")
public class ShpList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shpListId;

    @ManyToMany
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ShpList(List<Product> products, User user) {
        this.products = products;
        this.user = user;
    }
}
