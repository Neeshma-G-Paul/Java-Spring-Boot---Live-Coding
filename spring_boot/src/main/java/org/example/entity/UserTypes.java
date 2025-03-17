package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Table(name = "user_types")
@Entity
public class UserTypes {

    @Id
    private String type;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

}
