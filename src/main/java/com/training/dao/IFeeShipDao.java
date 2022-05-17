package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.FeeShipEntity;

@Repository
public interface IFeeShipDao extends JpaRepository<FeeShipEntity, Long>, JpaSpecificationExecutor<FeeShipEntity> {

	FeeShipEntity findByFeeShipId(Long feeShipId);
	
	FeeShipEntity findByWard_Id(String id);

	FeeShipEntity findByWard_IdAndFeeShipIdNot(String id, Long feeShipId);
}
