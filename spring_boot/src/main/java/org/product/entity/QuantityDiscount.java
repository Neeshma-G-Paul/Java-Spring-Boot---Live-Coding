package org.product.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "quantity_discounts")
@Entity
public class QuantityDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "min_quantity")
    private Integer minQuantity;

    @Column(name = "discount_percentage")
    private Double discountPercentage;
}
