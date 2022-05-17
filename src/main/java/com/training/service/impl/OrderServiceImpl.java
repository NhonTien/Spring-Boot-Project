package com.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.training.common.constant.Constants;
import com.training.dao.IOrderDao;
import com.training.dao.IOrderDetailDao;
import com.training.dao.jpaspec.OrderJpaSpecification;
import com.training.entity.Order;
import com.training.entity.OrderDetail;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IOrderDao orderDao;
	
	@Autowired
	IOrderDetailDao orderDetailsDao;

	@Override
	public Order findByOrderId(Long id) {
		return orderDao.findAllById(id);
	}
	
	@Override
	public List<Order> getAll() {
		return orderDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "orderDate");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<Order> orderEntitiesPage = orderDao.findAll(pageable);
			responseMap.put("ordersList", orderEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, orderEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all order: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel findOrderByIdApi(Long id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Order order = null;
		try {
			order = orderDao.findAllById(id);
			if (order != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding order by ID";
			LOGGER.error("Error when finding order by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, order);
	}

	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "id");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<Order> orderEntitiesPage = orderDao.findAll(OrderJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("orderList", orderEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, orderEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all order: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel findAllByIdApi(Long id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		List<OrderDetail> orderDetails = null;
		try {
			orderDetails = orderDetailsDao.findAllByOrder_Id(id);
			if (orderDetails != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding product by ID";
			LOGGER.error("Error when finding product by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, orderDetails);
	}
	
	@Override
    public ResponseDataModel confirmed(Order order){
    	
    	int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			order = orderDao.findAllById(order.getId());
			Boolean confirm = order.getConfirm();
			if (confirm == false) {
				order.setConfirm(true);
				orderDao.saveAndFlush(order);
				responseMsg = "Xác nhận thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}else{
				responseMsg = "Đơn hàng đã được xác nhận";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
//			orderDao.saveAndFlush(order);
//			responseMsg = "Order is confirmed successfully";
//			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = "Error when finding order by ID";
			LOGGER.error("Error when finding order by ID: ", e);
		}
        return new ResponseDataModel(responseCode, responseMsg, order);
    }
	
	@Override
    public ResponseDataModel cancelOrder(Order order){
    	
    	int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			order = orderDao.findAllById(order.getId());
			Boolean available = order.getAvailable();
			if (available == true) {
				order.setAvailable(false);
				orderDao.saveAndFlush(order);
				responseMsg = "Hủy đơn hàng thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}else{
				responseMsg = "Đơn hàng đã bị hủy";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
//			orderDao.saveAndFlush(order);
//			responseMsg = "Order is confirmed successfully";
//			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = "Error when finding order by ID";
			LOGGER.error("Error when finding order by ID: ", e);
		}
        return new ResponseDataModel(responseCode, responseMsg, order);
    }
	
	@Override
	public ResponseDataModel findAllByUserIdApi(Long userId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		List<Order> order = null;
		try {
			order = orderDao.findAllByUserEntity_UserId(userId);
			if (order != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding order by ID";
			LOGGER.error("Error when finding order by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, order);
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(Long userId, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "id");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<Order> orderEntitiesPage = orderDao.findAllByUserEntity_UserId(userId, pageable);
			responseMap.put("ordersList", orderEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, orderEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all order: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
	
}
