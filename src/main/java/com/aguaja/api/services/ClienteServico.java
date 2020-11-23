package com.aguaja.api.services;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Cliente;
import com.aguaja.api.repositories.ClienteRepositorio;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteServico {
	@Autowired
	private ClienteRepositorio repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert(Cliente obj) {
		repository.save(obj);
		return obj;
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}
	
	public Cliente update(Long id, Cliente obj) {

		try {
			Cliente entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
	
	private void updateData(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setSexo(obj.getSexo());
		entity.setEmail(obj.getEmail());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setLogin(obj.getLogin());
		entity.setSenha(obj.getSenha());
	}
}