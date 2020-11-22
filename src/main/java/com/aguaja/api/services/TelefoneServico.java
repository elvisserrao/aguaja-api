package com.aguaja.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Telefone;
import com.aguaja.api.repositories.TelefoneRepositorio;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class TelefoneServico {
	@Autowired
	private TelefoneRepositorio repository;
	
	public List<Telefone> findAll(){
		return repository.findAll();
	}
	
	public Telefone findById(Long id) {
		Optional<Telefone> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Telefone insert(Telefone obj) {
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
	
	public Telefone update(Long id, Telefone obj) {
		
		try {			
			Telefone entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Telefone entity, Telefone obj) {
		entity.setDdd(obj.getDdd());
		entity.setNumero(obj.getNumero());
		
	}
}
