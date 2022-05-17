package com.training.entity;

import javax.persistence.*;

@Entity
@Table(name = "Orderdetails")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PRICE")
	private Double price;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "AMOUNT")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
	private ProductEntity productEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

}
