package com.training.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DISTRICT")
public class District {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PREFIX")
	private String prefix;

	@ManyToOne
	@JoinColumn(name = "PROVINCE_ID", referencedColumnName = "ID")
	private Province province;

	@JsonIgnore
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private Set<Ward> wardSet;

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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Set<Ward> getWardSet() {
		return wardSet;
	}

	public void setWardSet(Set<Ward> wardSet) {
		this.wardSet = wardSet;
	}
}
