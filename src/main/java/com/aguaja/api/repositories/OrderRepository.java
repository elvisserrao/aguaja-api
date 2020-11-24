package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
