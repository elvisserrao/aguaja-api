package com.aguaja.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Stock;
import com.aguaja.api.repositories.StockRepository;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class StockService {
	@Autowired
	private StockRepository repository;
	
	public List<Stock> findAll(){
		return repository.findAll();
	}
	
	public Stock findById(Long id) {
		Optional<Stock> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Stock insert(Stock obj) {
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
	
	public Stock update(Long id, Stock obj) {
		
		try {			
			Stock entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Stock entity, Stock obj) {
		entity.setQuantity(obj.getQuantity());
		entity.setEntryDate(obj.getEntryDate());
		entity.setCostPrice(obj.getCostPrice());
		entity.setCostSell(obj.getCostSell());
	}
}
