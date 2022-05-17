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

import com.training.entity.CategoryEntity;
import com.training.model.ResponseDataModel;
import com.training.service.ICategoryService;

@Controller
@RequestMapping(value = { "admin/category" })
public class CategoryController {

	@Autowired
	ICategoryService categoryService;
	
	@GetMapping
	public String initPage(Model model) {
		return "category-index";
	}

	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findCategoryByIdApi(@RequestParam("id") Long categoryId) {
		return categoryService.findCategoryByIdApi(categoryId);
	}

	@GetMapping("/api/findAll/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("pageNumber") int pageNumber) {
		return categoryService.findAllWithPagerApi(pageNumber);
	}

	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addApi(@ModelAttribute CategoryEntity categoryEntity) {
		return categoryService.addApi(categoryEntity);
	}

	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@ModelAttribute CategoryEntity categoryEntity) {
		return categoryService.updateApi(categoryEntity);
	}

	@DeleteMapping(value = {"/api/delete/{categoryId}"})
	@ResponseBody
	public ResponseDataModel deleteApi(@PathVariable("categoryId") Long categoryId) {
		return categoryService.deleteApi(categoryId);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return categoryService.searchWithConditions(searchConditions, pageNumber);	
	}
	
	@GetMapping("/api/getAll")
	@ResponseBody
	public List<CategoryEntity> getAll() {
		return categoryService.getAll();
	}
	
	@GetMapping("/api/getAllCategory")
	@ResponseBody
	public List<CategoryEntity> getAllCategory() {
		return categoryService.getAllCategory();
	}
}
