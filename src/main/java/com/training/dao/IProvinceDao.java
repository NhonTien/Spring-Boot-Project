package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Province;

public interface IProvinceDao extends JpaRepository<Province, Long> {

	Province findAllById(String id);
}
