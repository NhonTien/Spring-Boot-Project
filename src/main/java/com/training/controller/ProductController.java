package com.training.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.entity.ProductEntity;
import com.training.model.ResponseDataModel;
import com.training.service.IProductService;

@Controller
@RequestMapping(value = { "admin/product" })
public class ProductController {

	@Autowired
	IProductService productService;

	@GetMapping
	public String initPage(Model model) {
		return "product-index";
	}
	
	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findBrandByIdApi(@RequestParam("id") Long productId) {
		return productService.findBrandByIdApi(productId);
	}

	@GetMapping("/api/findAll/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("pageNumber") int pageNumber) {
		return productService.findAllWithPagerApi(pageNumber);
	}

	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addApi(@ModelAttribute ProductEntity productEntity) {
		return productService.addApi(productEntity);
	}

	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@ModelAttribute ProductEntity productEntity) {
		return productService.updateApi(productEntity);
	}

	@DeleteMapping(value = {"/api/delete/{productId}"})
	@ResponseBody
	public ResponseDataModel deleteApi(@PathVariable("productId") Long productId) {
		return productService.deleteApi(productId);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return productService.searchWithConditions(searchConditions, pageNumber);	
	}
	
	@PostMapping(value = {"/api/searchWeb/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApiWeb(@RequestBody Map<String, Object> searchConditionsWeb, @PathVariable("pageNumber") int pageNumber) {
		return productService.searchWithConditionsWeb(searchConditionsWeb, pageNumber);	
	}
	
	@GetMapping("/api/getAll")
	@ResponseBody
	public List<ProductEntity> getAll() {
		return productService.getAll();
	}
	
	@GetMapping("/api/find/{productId}")
	@ResponseBody
	public ResponseDataModel findBrandByIdApi1(@PathVariable("productId") Long productId) {
		return productService.findBrandByIdApi(productId);
	}
	
	@GetMapping("/api/find1/{categoryId}")
	@ResponseBody
	public List<ProductEntity> findAllByCategoryIdApi(@PathVariable("categoryId") Long categoryId) {
		return productService.findAllByCategoryId(categoryId);
	}
	
	@GetMapping("/api/findAll1/{categoryId}/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("categoryId") Long categoryId, @PathVariable("pageNumber") int pageNumber) {
		return productService.findAllWithPagerApi(categoryId, pageNumber);
	}
}
