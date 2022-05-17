package com.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.entity.OrderDetail;

@Repository
public interface IOrderDetailDao extends JpaRepository<OrderDetail,Long> {

    List<OrderDetail> findAllByOrder_Id(Long id);
    
    List<OrderDetail> findAllByProductEntity_ProductId(Long productId);
    
}
