package com.aguaja.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
    Optional<Seller> findByUsername(String username);
}
