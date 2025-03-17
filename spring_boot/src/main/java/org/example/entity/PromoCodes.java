package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Table(name = "promo_codes")
@Entity
public class PromoCodes {

    @Id
    private String code;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "valid_until")
    private LocalDate validUntil;
}
