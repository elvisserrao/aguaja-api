package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
