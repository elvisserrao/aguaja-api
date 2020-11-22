package com.aguaja.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Venda;
import com.aguaja.api.repositories.VendaRepositorio;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class VendaServico {
	@Autowired
	private VendaRepositorio repository;
	
	public List<Venda> findAll(){
		return repository.findAll();
	}
	
	public Venda findById(Long id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Venda insert(Venda obj) {
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
	
	public Venda update(Long id, Venda obj) {
		
		try {			
			Venda entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Venda entity, Venda obj) {
		entity.setData(obj.getData());
		entity.setPreco(obj.getPreco());
		entity.setDesconto(obj.getDesconto());
		entity.setPreco_total(obj.getPreco_total());
		
	}
}
