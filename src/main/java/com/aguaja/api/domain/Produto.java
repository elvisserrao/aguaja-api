package com.aguaja.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double litros;
	private String descricao;

	@OneToMany(mappedBy = "produto")
	private List<Estoque> estoques = new ArrayList<>();

	@OneToMany(mappedBy = "id.produto")
	private Set<ItemVendido> items = new HashSet<>();

	private Produto() {
		super();
	}

	private  Produto(Integer id, String nome, Double litros, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.litros = litros;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLitros() {
		return litros;
	}
	public void setLitros(Double litros) {
		this.litros = litros;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	@JsonIgnore
	public Set<Venda> getVendas(){
		Set<Venda> set = new HashSet<>();
		for(ItemVendido  x : items) {
			set.add(x.getVenda());
		}
		return set;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
