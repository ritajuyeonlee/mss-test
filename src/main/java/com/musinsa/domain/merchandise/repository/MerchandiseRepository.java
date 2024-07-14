package com.musinsa.domain.merchandise.repository;

import com.musinsa.domain.merchandise.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise,Long> {
}
