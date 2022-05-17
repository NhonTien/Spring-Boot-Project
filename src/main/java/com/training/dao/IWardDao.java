package com.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.entity.Ward;

@Repository
public interface IWardDao extends JpaRepository<Ward, Long> {

	Ward findAllById(String id);
	
	List<Ward> findAllByDistrict_Id(String id);
}
