package com.aguaja.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Vendedor;
import com.aguaja.api.repositories.VendedorRepositorio;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class VendedorServico {
	@Autowired
	private VendedorRepositorio repository;
	
	public List<Vendedor> findAll(){
		return repository.findAll();
	}
	
	public Vendedor findById(Long id) {
		Optional<Vendedor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Vendedor insert(Vendedor obj) {
		repository.save(obj);
		return obj;
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public Vendedor update(Long id, Vendedor obj) {
		
		try {			
			Vendedor entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Vendedor entity, Vendedor obj) {
		entity.setNome(obj.getNome());
		entity.setSexo(obj.getSexo());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setLogin(obj.getLogin());
		entity.setSenha(obj.getSenha());
		entity.setEmail(obj.getEmail());
		entity.setAdmin(obj.getAdmin());
		
		
	}
}
