package com.aguaja.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Estoque;
import com.aguaja.api.repositories.EstoqueRepositorio;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class EstoqueServico {
	@Autowired
	private EstoqueRepositorio repository;
	
	public List<Estoque> findAll(){
		return repository.findAll();
	}
	
	public Estoque findById(Long id) {
		Optional<Estoque> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Estoque insert(Estoque obj) {
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
	
	public Estoque update(Long id, Estoque obj) {
		
		try {			
			Estoque entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Estoque entity, Estoque obj) {
		entity.setQuantidade(obj.getQuantidade());
		entity.setData_entrada(obj.getData_entrada());
		entity.setPreco_custo(obj.getPreco_custo());
		entity.setPreco_venda(obj.getPreco_venda());
	}
}
