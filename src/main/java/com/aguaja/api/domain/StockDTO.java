package com.aguaja.api.domain;
import java.sql.Date;

public class StockDTO {
	private Long id;
	private Integer quantity;
	private Date entryDate;
	private Double costPrice;
	private Double costSell;
	private Long sellerId;
	private Long productId;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
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

	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
