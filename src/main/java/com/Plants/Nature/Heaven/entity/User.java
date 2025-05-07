package com.Plants.Nature.Heaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")  // changed from "user" to "users"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private String address;
    private String role;
}
