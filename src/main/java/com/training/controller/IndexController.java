package com.training.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.entity.BrandEntity;
import com.training.entity.CommentEntity;
import com.training.entity.Order;
import com.training.entity.UserEntity;
import com.training.security.UserService;
import com.training.service.IBrandService;
import com.training.service.ICommentService;
import com.training.service.IOrderService;
import com.training.service.StatsService;

@Controller
@RequestMapping(value = { "/admin" })
public class IndexController {
	
	@Autowired
	IOrderService orderService;

	@Autowired
	StatsService statsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ICommentService commentService;
	
	@Autowired
	IBrandService brandService;
	
	@GetMapping(value = { "/home" })
	public String initPage(Model model) {

		List<Order> orderList = orderService.getAll();
		long count = 0;
		for (Order order : orderList) {
			long millis = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(millis);
			String currentDate = sdf.format(date);
			String orderDate = sdf.format(order.getOrderDate());
			if(order.getId() != null && currentDate.compareTo(orderDate) == 0) {
				count++;
				model.addAttribute("count", count);
			} else {
				model.addAttribute("count", count);
			}
		}
		
		List<UserEntity> userEntity = userService.getAll();
		long countUser = 0;
		for (UserEntity user : userEntity) {
			if(user.getRole().equals("ROLE_USER")) {
				countUser++;
				model.addAttribute("user", countUser);
			} else {
				model.addAttribute("user", countUser);
			}
		}
		
		List<CommentEntity> commentEntity = commentService.getAll();
		long countComment = 0;
		for (CommentEntity comment : commentEntity) {
			if(comment.getCommentId() != null) {
				countComment++;
				model.addAttribute("comment", countComment);
			} else {
				model.addAttribute("comment", countComment);
			}
		}
		
		List<BrandEntity> brandEntity = brandService.getAll();
		long countBrand = 0;
		for (BrandEntity brand : brandEntity) {
			if(brand.getBrandId() != null) {
				countBrand++;
				model.addAttribute("brand", countBrand);
			} else {
				model.addAttribute("brand", countBrand);
			}
		}
		
		model.addAttribute("categoryStats", statsService.categoryStats());
		model.addAttribute("productMonthStats", statsService.productMonthStats(null, null, null));
		return "index";
	}
	
	@GetMapping(value = {"/login"})
	public String loginPage() {
		return "login";
	}
	
	@GetMapping(value = {"/homepage"})
	public String homePage1() {
		return "homepage";
	}
	
	@GetMapping(value = {"/view-profile"})
	public String profilePage() {
		return "view-profile";
	}
}