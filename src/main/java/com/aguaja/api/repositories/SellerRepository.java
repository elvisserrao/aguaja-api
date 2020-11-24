package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{

}
