package com.cooksy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_list")
public class ShpList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shpListId;

    private String date;


    public ShpList(String date) {
        this.date = date;
    }
}
