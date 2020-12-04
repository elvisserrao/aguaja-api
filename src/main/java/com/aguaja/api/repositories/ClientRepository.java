package com.aguaja.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Client;



public interface ClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findByUsername(String username);
}
