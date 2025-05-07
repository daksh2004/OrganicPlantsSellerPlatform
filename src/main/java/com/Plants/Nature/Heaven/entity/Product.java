package com.Plants.Nature.Heaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProductID;

    private String Name;

    private String description;

    @Column(name = "Stock_quantity")
    private int Stock_quantity;

    @Column(name = "Price")
    private double Price;

    
}
