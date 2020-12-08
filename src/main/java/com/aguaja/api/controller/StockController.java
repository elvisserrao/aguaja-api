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

import com.aguaja.api.domain.Product;
import com.aguaja.api.domain.Seller;
import com.aguaja.api.domain.Stock;
import com.aguaja.api.domain.StockDTO;
import com.aguaja.api.services.StockServico;
import com.aguaja.api.services.SellerServico;
import com.aguaja.api.services.ProductServico;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	private StockServico stockService;

	@Autowired
	private SellerServico sellerService;

	@Autowired
	private ProductServico produtoService;
	
	@GetMapping
	public ResponseEntity<List<Stock>> findAll(){
		List<Stock> list = stockService.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Stock> findById(@PathVariable Long id) {
		Stock obj = stockService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Stock> insert(@RequestBody StockDTO obj){
		Product product = produtoService.findById(obj.getProductId());
		Seller seller = sellerService.findById(obj.getSellerId());
		Stock stock = new Stock();

		stock.setCostPrice(obj.getCostPrice());
		stock.setCostSell(obj.getCostSell());
		stock.setEntryDate(obj.getEntryDate());
		stock.setQuantity(obj.getQuantity());
		stock.setProduct(product);
		stock.setSeller(seller);
		
		stock = stockService.insert(stock);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stock.getId()).toUri();
		return ResponseEntity.created(uri).body(stock);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete( @PathVariable Long id){
		stockService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Stock> update(@PathVariable Long id, @RequestBody Stock obj){
		obj = stockService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
