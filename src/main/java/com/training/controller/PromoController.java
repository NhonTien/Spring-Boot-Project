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

import com.training.entity.PromoEntity;
import com.training.model.ResponseDataModel;
import com.training.service.IPromoService;

@Controller
@RequestMapping(value = { "admin/promo" })
public class PromoController {

	@Autowired
	IPromoService promoService;
	
	@GetMapping
	public String initPage(Model model) {
		return "promo-index";
	}

	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findPromoByIdApi(@RequestParam("id") Long promoId) {
		return promoService.findPromoByIdApi(promoId);
	}
	
	@GetMapping("/api/find1")
	@ResponseBody
	public ResponseDataModel findPromoByPromoCodeApi(@RequestParam("promoCode") String promoCode) {
		return promoService.findPromoByPromoCodeApi(promoCode);
	}

	@GetMapping("/api/findAll/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("pageNumber") int pageNumber) {
		return promoService.findAllWithPagerApi(pageNumber);
	}

	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addApi(@ModelAttribute PromoEntity promoEntity) {
		return promoService.addApi(promoEntity);
	}

	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@ModelAttribute PromoEntity promoEntity) {
		return promoService.updateApi(promoEntity);
	}

	@DeleteMapping(value = {"/api/delete/{promoId}"})
	@ResponseBody
	public ResponseDataModel deleteApi(@PathVariable("promoId") Long promoId) {
		return promoService.deleteApi(promoId);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return promoService.searchWithConditions(searchConditions, pageNumber);	
	}
	
	@GetMapping("/api/getAll")
	@ResponseBody
	public List<PromoEntity> getAll() {
		return promoService.getAll();
	}
}
