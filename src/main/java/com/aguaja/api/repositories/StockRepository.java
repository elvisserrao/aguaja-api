package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{

}
