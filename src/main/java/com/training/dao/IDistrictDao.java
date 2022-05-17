package com.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.District;

public interface IDistrictDao extends JpaRepository<District,Long> {
	
	District findAllById(String id);
	
    List<District> findAllByProvince_Id(String id);
}