package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.Order;
import com.training.model.ResponseDataModel;

public interface IOrderService {

	Order findByOrderId(Long id);
	
	List<Order> getAll();

	ResponseDataModel findAllWithPagerApi(int pageNumber);

	ResponseDataModel findOrderByIdApi(Long id);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);

	ResponseDataModel findAllByIdApi(Long id);

	ResponseDataModel confirmed(Order order);

	ResponseDataModel findAllByUserIdApi(Long userId);

	ResponseDataModel findAllWithPagerApi(Long userId, int pageNumber);

	ResponseDataModel cancelOrder(Order order);


}
