package com.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FEESHIP")
public class FeeShipEntity {

	@Id
	@Column(name = "FEESHIP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feeShipId;
	
	@Column(name = "FEESHIP")
	private Double feeShip;

	@JoinColumn(name = "PROVINCE_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Province province;

	@JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private District district;

	@JoinColumn(name = "WARD_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Ward ward;

	public Long getFeeShipId() {
		return feeShipId;
	}

	public void setFeeShipId(Long feeShipId) {
		this.feeShipId = feeShipId;
	}

	public Double getFeeShip() {
		return feeShip;
	}

	public void setFeeShip(Double feeShipp) {
		this.feeShip = feeShipp;
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
}
