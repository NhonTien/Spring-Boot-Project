package com.training.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "APT_SUIT")
	private String aptSuit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE")
	private Date orderDate;

	@Column(name = "TOTAL_QUANTITY")
	private int totalQuantity;

	@Column(name = "TOTAL")
	private Double total;

	@Column(name = "AVAILABLE")
	private Boolean available;

	@Column(name = "CONFIRM")
	private Boolean confirm;

	@Column(name = "ORDER_CODE")
	private String orderCode;
	
	@Column(name = "FEESHIP")
	private Double feeShip;
	
	@Column(name = "PROMO_CODE")
	private String promoCode;
	
	@Column(name = "DISCOUNT")
	private Double discount;
	
	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;

	@JsonIgnore
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private Set<OrderDetail> orderDetailsSet;

	@JoinColumn(name = "PROVINCE_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Province province;

	@JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private District district;

	@JoinColumn(name = "WARD_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Ward ward;

	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity userEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAptSuit() {
		return aptSuit;
	}

	public void setAptSuit(String aptSuit) {
		this.aptSuit = aptSuit;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Double getFeeShip() {
		return feeShip;
	}

	public void setFeeShip(Double feeShip) {
		this.feeShip = feeShip;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Set<OrderDetail> getOrderDetailsSet() {
		return orderDetailsSet;
	}

	public void setOrderDetailsSet(Set<OrderDetail> orderDetailsSet) {
		this.orderDetailsSet = orderDetailsSet;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public Order() {
		super();
		orderDate = new Date();
		orderCode = java.util.UUID.randomUUID().toString();
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
