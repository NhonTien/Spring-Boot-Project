package com.training.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {
	
	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CATEGORY_SLUG")
	private String categorySlug;

	@JsonIgnore
	@OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY)
	private Set<ProductEntity> productSet;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public Set<ProductEntity> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<ProductEntity> productSet) {
		this.productSet = productSet;
	}
}
