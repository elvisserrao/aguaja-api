package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{

}
