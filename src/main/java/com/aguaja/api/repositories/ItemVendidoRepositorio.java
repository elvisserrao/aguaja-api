package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.OrderItem;

public interface ItemVendidoRepositorio extends JpaRepository<OrderItem, Long>{

}
