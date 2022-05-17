package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.training.entity.BannerEntity;

public interface IBannerDao extends JpaRepository<BannerEntity, Long>, JpaSpecificationExecutor<BannerEntity> {
	
	BannerEntity findByBannerId(Long bannerId);
	
	BannerEntity findByBannerName(String bannerName);
	
	BannerEntity findByBannerNameAndBannerIdNot(String bannerName, Long bannerId);
}
