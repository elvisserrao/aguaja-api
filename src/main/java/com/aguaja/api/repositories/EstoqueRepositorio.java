package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Estoque;

public interface EstoqueRepositorio extends JpaRepository<Estoque, Long>{

}
