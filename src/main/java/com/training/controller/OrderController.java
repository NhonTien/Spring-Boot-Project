package com.training.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.entity.Order;
import com.training.model.ResponseDataModel;
import com.training.service.IOrderService;

@Controller
@RequestMapping(value = { "/admin/order" })
public class OrderController {
	
	@Autowired
	IOrderService orderService;

	@GetMapping
	public String initPage() {
		return "order-index";
	}
	
	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findOrderByIdApi(@RequestParam("id") Long id) {
		return orderService.findOrderByIdApi(id);
	}

	@GetMapping("/api/findAll/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("pageNumber") int pageNumber) {
		return orderService.findAllWithPagerApi(pageNumber);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return orderService.searchWithConditions(searchConditions, pageNumber);	
	}
	
	@GetMapping("/api/find/{id}")
	@ResponseBody
	public ResponseDataModel findAllByIdApi(@PathVariable("id") Long id) {
		return orderService.findAllByIdApi(id);
	}
	
	@GetMapping("/api/finds")
	@ResponseBody
	public ResponseDataModel findAllByIdApi1(@RequestParam("id") Long id) {
		return orderService.findAllByIdApi(id);
	}
	
	@GetMapping("/api/find1/{userId}")
	@ResponseBody
	public ResponseDataModel findAllByIdApi2(@PathVariable("userId") Long userId) {
		return orderService.findAllByUserIdApi(userId);
	}
	
	@GetMapping("/api/findss")
	@ResponseBody
	public ResponseDataModel findAllByIdApi3(@RequestParam("userId") Long userId) {
		return orderService.findAllByUserIdApi(userId);
	}
	
	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@ModelAttribute Order order) {
		return orderService.confirmed(order);
	}
	
	@PostMapping(value = {"/api/updateOrder"})
	@ResponseBody
	public ResponseDataModel updateOrderApi(@ModelAttribute Order order) {
		return orderService.cancelOrder(order);
	}
	
	@GetMapping("/api/findAll/{userId}/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("userId") Long userId, @PathVariable("pageNumber") int pageNumber) {
		return orderService.findAllWithPagerApi(userId, pageNumber);
	}
}
