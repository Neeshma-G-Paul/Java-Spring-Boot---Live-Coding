package org.product.repository;

import org.product.entity.PromoCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodesRepository extends JpaRepository<PromoCodes, String> {
}
