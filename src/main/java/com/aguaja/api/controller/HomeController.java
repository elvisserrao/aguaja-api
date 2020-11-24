package com.aguaja.api.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aguaja.api.domain.Order;
import com.aguaja.api.domain.OrderItem;
import com.aguaja.api.domain.Product;
import com.aguaja.api.domain.Stock;
import com.aguaja.api.domain.enums.OrderStatus;
import com.aguaja.api.repositories.OrderItemRepository;
import com.aguaja.api.repositories.OrderRepository;
import com.aguaja.api.repositories.ProductRepository;
import com.aguaja.api.repositories.StockRepository;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	StockRepository stocks;
	@Autowired
	OrderItemRepository items;
	@Autowired
	OrderRepository vendas;
	@Autowired
	ProductRepository products;

	@GetMapping("")
	public Order welcome() {
		Product product = new Product(null, "Agua da gente", 20.00, "Qualidade da bao terra");
		products.save(product);
		
		Stock stock = new Stock(null, product, 1, Instant.parse("2019-06-20T19:53:07Z"), 20.00, 25.00);
		stocks.save(stock);
		
		Order order = new Order(null, Instant.parse("2020-06-20T19:53:07Z"), 20.00, 25.00, 30.00,
				OrderStatus.OPEN);
		OrderItem item = new OrderItem(order, stock, 3);

		order.getItems().add(item);
		vendas.save(order);

		return order;
	}
}
