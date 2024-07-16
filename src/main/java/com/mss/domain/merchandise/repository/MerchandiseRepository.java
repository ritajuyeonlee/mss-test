package com.mss.domain.merchandise.repository;

import com.mss.domain.merchandise.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise,Long> {
}
