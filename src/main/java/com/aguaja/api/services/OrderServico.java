package com.aguaja.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aguaja.api.domain.Order;
import com.aguaja.api.domain.OrderItem;
import com.aguaja.api.domain.Seller;
import com.aguaja.api.domain.enums.OrderStatus;
import com.aguaja.api.repositories.OrderItemRepository;
import com.aguaja.api.repositories.OrderRepository;
import com.aguaja.api.repositories.SellerRepository;
import com.aguaja.api.services.exceptions.DatabaseException;
import com.aguaja.api.services.exceptions.ResourceNotFoundException;

@Service
public class OrderServico {
	@Autowired
	private OrderRepository repository;

	@Autowired
	SellerServico sellerService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	
	public List<Order> findByStatusOpen() {
		List<Order> obj = repository.findByOrderStatus(1);
		return obj;
	}

	public Order findBySellerId(Long id) {
		Seller seller = sellerService.findById(id);
		Optional<Order> obj = repository.findBySeller(seller);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Order insert(Order order, OrderItem item) {
		order.getItems().add(item);
		repository.save(order);
		orderItemRepository.save(item);
		return order;
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
	
	public Order update(Long id, Order obj) {
		
		try {			
			Order entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Order entity, Order obj) {
		entity.setDate(obj.getDate());
		entity.setPrice(obj.getPrice());
		entity.setDiscount(obj.getDiscount());
		entity.setPriceTotal(obj.getPriceTotal());
		entity.setOrderStatus(obj.getOrderStatusId());
	}
}
