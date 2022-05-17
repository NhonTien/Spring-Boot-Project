package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.entity.BannerEntity;
import com.training.entity.UserEntity;
import com.training.model.ResponseDataModel;
import com.training.security.UserService;
import com.training.service.IBannerService;
import com.training.service.ICategoryService;
import com.training.service.IProductService;
import com.training.service.ShoppingCartService;

@Controller
@RequestMapping(value = { "/" })
public class WebController {
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IBannerService bannerService;
	
	@Autowired
	ShoppingCartService cartService;
	
	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String initPage(Model model){
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		model.addAttribute("banner", getAll());
		return "web";
	}
	
	@GetMapping(value = {"/login"})
	public String loginPage(){
		return "login-user";
	}
	
	@GetMapping("/api/getAll")
	@ResponseBody
	public List<BannerEntity> getAll() {
		return bannerService.getAll();
	}
	
	@GetMapping(value = {"/{categorySlug}"})
	public String initCategoryPage(@PathVariable(name = "categorySlug") String categorySlug, Model model) {
		model.addAttribute("categoryEntity", categoryService.findByCategorySlug(categorySlug));
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		model.addAttribute("banner", getAll());
		return "product-category";
	}
	
	@GetMapping(value = {"/productdetail/{categorySlug}/{productSlug}"})
	public String initUpdatePage(@PathVariable(name = "categorySlug") String categorySlug, @PathVariable(name = "productSlug") String productSlug, Model model) {
		model.addAttribute("productEntity", productService.findByProductSlug(productSlug));
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		return "product-details";
	}
	
	@PostMapping("/api/findUser/{username}")
	@ResponseBody
	public UserEntity findByUserName(@PathVariable(name = "username") String username) {
		return userService.findByUsername(username);
	}
	
	@PostMapping("/api/findUser")
	@ResponseBody
	public ResponseDataModel findByUserId(@RequestParam("userId") Long userId) {
		return userService.findUserByIdApi(userId);
	}
	
	@GetMapping("/register")
    public String registration() {
        return "register";
    }

	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addApi(@ModelAttribute UserEntity userEntity) {
		return userService.addApi(userEntity);
	}
	
	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@ModelAttribute UserEntity userEntity) {
		return userService.updateApi(userEntity);
	}
	
	@GetMapping(value = { "/order-history" })
	public String orderHistoryPage(Model model) {
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		return "order-history";
	}
	
	@GetMapping(value = { "/view-profile" })
	public String profilePage(Model model) {
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		return "user-profile";
	}
}
