package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.qos.logback.core.net.server.Client;

public interface ClienteRepositorio extends JpaRepository<Client, Long>{

}
