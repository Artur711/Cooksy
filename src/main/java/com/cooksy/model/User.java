package com.cooksy.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photoUrl;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_TYPE_ID")
    private UserType userType;
}
