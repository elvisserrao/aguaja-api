package com.aguaja.api.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Cliente;
import com.aguaja.api.repositories.ClienteRepositorio;

@Service
public class ClienteServico {
	@Autowired
	private ClienteRepositorio repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	
}