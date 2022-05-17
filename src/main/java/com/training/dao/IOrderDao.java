package com.training.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.Order;

@Repository
public interface IOrderDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
	
    Order findAllById(Long id);
    
    List<Order> findAllByUserEntity_UserId(Long userId);

	Page<Order> findAllByUserEntity_UserId(Long userId, Pageable pageable);
}

