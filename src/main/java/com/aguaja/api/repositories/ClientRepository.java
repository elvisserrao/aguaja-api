package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Client;



public interface ClientRepository extends JpaRepository<Client, Long>{

}
