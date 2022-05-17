package com.training.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.entity.BannerEntity;
import com.training.model.ResponseDataModel;
import com.training.service.IBannerService;

@Controller
@RequestMapping(value = { "admin/banner" })
public class BannerController {
	
	@Autowired
	IBannerService bannerService;
	
	@GetMapping
	public String initPage() {
		return "banner-index";
	}
	
	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findBannerByIdApi(@RequestParam("id") Long bannerId) {
		return bannerService.findBannerByIdApi(bannerId);
	}

	@GetMapping("/api/findAll/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("pageNumber") int pageNumber) {
		return bannerService.findAllWithPagerApi(pageNumber);
	}

	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addApi(@ModelAttribute BannerEntity bannerEntity) {
		return bannerService.addApi(bannerEntity);
	}

	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@ModelAttribute BannerEntity bannerEntity) {
		return bannerService.updateApi(bannerEntity);
	}

	@DeleteMapping(value = {"/api/delete/{bannerId}"})
	@ResponseBody
	public ResponseDataModel deleteApi(@PathVariable("bannerId") Long bannerId) {
		return bannerService.deleteApi(bannerId);
	}
	
	@GetMapping("/api/getAll")
	@ResponseBody
	public List<BannerEntity> getAll() {
		return bannerService.getAll();
	}
	
	@GetMapping("/api/find/{bannerId}")
	@ResponseBody
	public ResponseDataModel findBannerByIdApi1(@PathVariable("bannerId") Long bannerId) {
		return bannerService.findBannerByIdApi(bannerId);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return bannerService.searchWithConditions(searchConditions, pageNumber);	
	}
}
