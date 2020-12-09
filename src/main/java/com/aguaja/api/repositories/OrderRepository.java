package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.aguaja.api.domain.Order;
import com.aguaja.api.domain.Seller;

public interface OrderRepository extends JpaRepository<Order, Long>{
   Optional<Order> findBySeller(Seller seller);
   List<Order> findByOrderStatus(Integer orderStatus);
}
