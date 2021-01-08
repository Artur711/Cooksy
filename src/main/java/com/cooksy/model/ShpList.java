package com.cooksy.model;

import com.cooksy.model.api.SpCuProduct;
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
    private List<SpCuProduct> spCuProducts;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ShpList(List<SpCuProduct> spCuProducts, User user) {
        this.spCuProducts = spCuProducts;
        this.user = user;
    }
}
