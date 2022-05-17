package com.training.controller;

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

import com.training.entity.FeeShipEntity;
import com.training.model.ResponseDataModel;
import com.training.service.IFeeShipService;

@Controller
@RequestMapping(value = { "admin/feeship" })
public class FeeShipController {
	
	@Autowired
	IFeeShipService feeShipService;
	
	@GetMapping
	public String initPage(Model model) {
		return "feeship-index";
	}

	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findFeeShipByIdApi(@RequestParam("id") Long feeShipId) {
		return feeShipService.findFeeShipByIdApi(feeShipId);
	}
	
	@GetMapping("/api/find1")
	@ResponseBody
	public ResponseDataModel findFeeShipByWardIdApi(@RequestParam("id") String id) {
		return feeShipService.findFeeShipByWardIdApi(id);
	}

	@GetMapping("/api/findAll/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("pageNumber") int pageNumber) {
		return feeShipService.findAllWithPagerApi(pageNumber);
	}

	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addApi(@ModelAttribute FeeShipEntity feeShipEntity) {
		return feeShipService.addApi(feeShipEntity);
	}

	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@ModelAttribute FeeShipEntity feeShipEntity) {
		return feeShipService.updateApi(feeShipEntity);
	}

	@DeleteMapping(value = {"/api/delete/{feeShipId}"})
	@ResponseBody
	public ResponseDataModel deleteApi(@PathVariable("feeShipId") Long feeShipId) {
		return feeShipService.deleteApi(feeShipId);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return feeShipService.searchWithConditions(searchConditions, pageNumber);	
	}
}
