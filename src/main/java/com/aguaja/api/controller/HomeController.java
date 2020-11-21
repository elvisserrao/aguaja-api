package com.aguaja.api.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aguaja.api.domain.Estoque;
import com.aguaja.api.domain.ItemVendido;
import com.aguaja.api.domain.Produto;
import com.aguaja.api.domain.Venda;
import com.aguaja.api.domain.enums.VendaStatus;

import com.aguaja.api.repositories.EstoqueRepositorio;
import com.aguaja.api.repositories.ItemVendidoRepositorio;
import com.aguaja.api.repositories.ProdutoRepositorio;
import com.aguaja.api.repositories.VendaRepositorio;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	EstoqueRepositorio estoques;
	@Autowired
	ItemVendidoRepositorio items;
	@Autowired
	VendaRepositorio vendas;
	@Autowired
	ProdutoRepositorio produtos;

	@GetMapping("")
	public Venda welcome() {
		Produto produto = new Produto(null, "Agua da gente", 20.00, "Qualidade da bao terra");
		produtos.save(produto);
		
		Estoque estoque = new Estoque(null, produto, 1, Instant.parse("2019-06-20T19:53:07Z"), 20.00, 25.00);
		estoques.save(estoque);
		
		Venda venda = new Venda(null, Instant.parse("2020-06-20T19:53:07Z"), 20.00, 25.00, 30.00,
				VendaStatus.EM_ABERTO);
		ItemVendido item = new ItemVendido(venda, estoque, 3);

		venda.getItems().add(item);
		vendas.save(venda);

		return venda;
	}
}
