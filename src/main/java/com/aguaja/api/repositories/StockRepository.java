package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.aguaja.api.domain.Stock;
@Transactional
public interface StockRepository extends JpaRepository<Stock, Long>{

}
