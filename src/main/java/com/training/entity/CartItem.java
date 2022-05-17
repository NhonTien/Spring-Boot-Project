package com.training.entity;

public class CartItem {
	private Long productId;
	private String image;
	private String name;
	private double price;
	private double discount;
	private String category;
	private String categorySlug;
	private String productSlug;
	private int qty = 1;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public String getProductSlug() {
		return productSlug;
	}

	public void setProductSlug(String productSlug) {
		this.productSlug = productSlug;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public CartItem() {
		super();
	}

	public CartItem(Long productId, String name, double price, int qty) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.qty = qty;
	}

	public CartItem(Long productId, String image, String name, String category, String productSlug, double price, double discount, int qty) {
		super();
		this.productId = productId;
		this.image = image;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.category = category;
		this.productSlug = productSlug;
		this.qty = qty;
	}
	
	public double getAmount() {
        return this.price*(1 - this.discount/100) * this.qty;
    }
}
