package com.aguaja.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aguaja.api.domain.Order;
import com.aguaja.api.domain.OrderDTO;
import com.aguaja.api.domain.OrderItem;
import com.aguaja.api.domain.Seller;
import com.aguaja.api.domain.Stock;
import com.aguaja.api.domain.Client;
import com.aguaja.api.services.ClientService;
import com.aguaja.api.services.OrderServico;
import com.aguaja.api.services.SellerServico;
import com.aguaja.api.services.StockServico;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderServico orderService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SellerServico sellerService;

	@Autowired
	private StockServico stockService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = orderService.findById(id);
		return ResponseEntity.ok().body(obj);
	
	}

	@GetMapping(value = "/open")
	public ResponseEntity<List<Order>> findByStatusOpen() {
		List<Order> obj = orderService.findByStatusOpen();
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody OrderDTO obj){
		Seller seller = sellerService.findById(obj.getSellerId());
		Client client = clientService.findById(obj.getClientId());
		Stock stock = stockService.findById(obj.getStockId());

		Order order = new Order();

		order.setDate(obj.getDate());
		order.setOrderStatus(obj.getOrderStatus());
		order.setPrice(obj.getPrice());
		order.setSeller(seller);
		order.setClient(client);

		OrderItem item = new OrderItem(order, stock, obj.getQuantity());
		
		order = orderService.insert(order, item);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(order);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete( @PathVariable Long id){
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj){
		obj = orderService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
