package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
