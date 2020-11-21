package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{

}
