package com.training.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PROVINCE")
public class Province {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PREFIX")
	private String prefix;

	@Column(name = "SLUG")
	private String slug;

	@JsonIgnore
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
	private Set<District> districtSet;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Set<District> getDistrictSet() {
		return districtSet;
	}

	public void setDistrictSet(Set<District> districtSet) {
		this.districtSet = districtSet;
	}
}
