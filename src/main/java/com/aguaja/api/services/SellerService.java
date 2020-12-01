package com.aguaja.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Seller;
import com.aguaja.api.repositories.SellerRepository;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class SellerService {
	@Autowired
	private SellerRepository repository;
	
	public List<Seller> findAll(){
		return repository.findAll();
	}
	
	public Seller findById(Long id) {
		Optional<Seller> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Seller insert(Seller obj) {
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
	
	public Seller update(Long id, Seller obj) {
		
		try {			
			Seller entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Seller entity, Seller obj) {
		entity.setName(obj.getName());
		entity.setGender(obj.getGender());
		entity.setBirthDate(obj.getBirthDate());
		entity.setLogin(obj.getLogin());
		entity.setPassword(obj.getPassword());
		entity.setEmail(obj.getEmail());
		entity.setAdmin(obj.getAdmin());
		
		
	}
}
