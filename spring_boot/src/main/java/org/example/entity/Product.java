package org.example.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "products")
@Entity
public class Product {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "base_price")
    private Double basePrice;

    @Column(name="category")
    private String category;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

}
