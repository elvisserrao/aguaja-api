package com.aguaja.api.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_stock")
public class Stock implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantity;
	private Instant entryDate;
	private Double costPrice;
	private Double costSell;

	@ManyToOne
	@JoinColumn(name="seller_id")
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	@OneToMany(mappedBy = "id.stock")
	private Set<OrderItem> items = new HashSet<>();

	public Stock() {
		super();
	}

	public Stock(Integer id, Product product, Integer quantity, Instant entryDate, Double costPrice, Double costSell, Seller seller) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.entryDate = entryDate;
		this.costPrice = costPrice;
		this.costSell = costSell;
		this.seller = seller;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Instant getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Instant entryDate) {
		this.entryDate = entryDate;
	}

	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getCostSell() {
		return costSell;
	}
	public void setCostSell(Double costSell) {
		this.costSell = costSell;
	}

	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for(OrderItem  x : items) {
			set.add(x.getOrder());
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
		Stock other = (Stock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
