package org.example.repository;

import org.example.entity.QuantityDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityDiscountRepository extends JpaRepository<QuantityDiscount, Integer> {

    QuantityDiscount findByMinQuantity(Integer quantity);
}
