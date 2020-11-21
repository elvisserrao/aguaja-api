package com.aguaja.api.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "tb_item_vendido")
public class ItemVendido implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemVendidoPK id = new ItemVendidoPK();

	private Integer quantidade;

	public ItemVendido() {
	}

	public ItemVendido(Venda venda, Estoque estoque,Integer quantidade) {
		id.setVenda(venda);
		id.setEstoque(estoque);
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return id.getVenda();
	}
	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}

	public Estoque getEstoque() {
		return id.getEstoque();
	}
	public void setEstoque(Estoque estoque) {
		id.setEstoque(estoque);
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		ItemVendido other = (ItemVendido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
