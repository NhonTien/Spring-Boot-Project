package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.PromoEntity;

@Repository
public interface IPromoDao extends JpaRepository<PromoEntity, Long>, JpaSpecificationExecutor<PromoEntity> {
	
	PromoEntity findByPromoId(Long promoId);
	
	PromoEntity findByPromoName(String promoName);
	
	PromoEntity findByPromoCode(String promoCode);

	PromoEntity findByPromoNameAndPromoIdNot(String promoName, Long promoId);

	PromoEntity findByPromoCodeAndPromoIdNot(String promoName, Long promoId);
}
