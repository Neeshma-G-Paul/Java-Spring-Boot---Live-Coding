package org.example.repository;

import org.example.entity.PromoCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodesRepository extends JpaRepository<PromoCodes, String> {
}
