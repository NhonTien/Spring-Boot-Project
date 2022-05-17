package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.entity.District;
import com.training.entity.Province;
import com.training.entity.Ward;
import com.training.model.ResponseDataModel;
import com.training.service.IDistrictService;
import com.training.service.IProvinceService;
import com.training.service.IWardService;

@Controller
@RequestMapping(value = { "admin/address" })
public class AddressController {
	
	@Autowired
	IProvinceService provinceService;
	
	@Autowired
	IDistrictService districtService;
	
	@Autowired
	IWardService wardService;
	
	@GetMapping
	public String initPage() {
		return "address-index";
	}
	
	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findProvinceByIdApi(@RequestParam("id") String id) {
		return provinceService.findProvinceByIdApi(id);
	}
	
	@GetMapping("/api/getAll")
	@ResponseBody
	public List<Province> getAll() {
		return provinceService.getAll();
	}
	
	@GetMapping("/api/find/{id}")
	@ResponseBody
	public ResponseDataModel findProvinceByIdApi1(@PathVariable("id") String id) {
		return provinceService.findProvinceByIdApi(id);
	}
	
	@GetMapping("/api/finds/{id}")
	@ResponseBody
	public ResponseDataModel findProvinceByIdApi2(@PathVariable("id") String id) {
		return provinceService.findAllByIdApi(id);
	}
	
	@GetMapping("/api/findss")
	@ResponseBody
	public ResponseDataModel findProvinceByIdApi3(@RequestParam("id") String id) {
		return provinceService.findAllByIdApi(id);
	}
	
	@GetMapping("/api/find1")
	@ResponseBody
	public ResponseDataModel findDistrictByIdApi(@RequestParam("id") String id) {
		return districtService.findDistrictByIdApi(id);
	}
	
	@GetMapping("/api/getAll1")
	@ResponseBody
	public List<District> getAll1() {
		return districtService.getAll();
	}
	
	@GetMapping("/api/find1/{id}")
	@ResponseBody
	public ResponseDataModel findDistrictByIdApi1(@PathVariable("id") String id) {
		return districtService.findDistrictByIdApi(id);
	}
	
	@GetMapping("/api/finds1/{id}")
	@ResponseBody
	public ResponseDataModel findDistrictByIdApi2(@PathVariable("id") String id) {
		return districtService.findAllByIdApi(id);
	}
	
	@GetMapping("/api/findss1")
	@ResponseBody
	public ResponseDataModel findDistrictByIdApi3(@RequestParam("id") String id) {
		return districtService.findAllByIdApi(id);
	}
	
	@GetMapping("/api/find2")
	@ResponseBody
	public ResponseDataModel findWardByIdApi(@RequestParam("id") String id) {
		return wardService.findWardByIdApi(id);
	}
	
	@GetMapping("/api/getAll2")
	@ResponseBody
	public List<Ward> getAll2() {
		return wardService.getAll();
	}
	
	@GetMapping("/api/find2/{id}")
	@ResponseBody
	public ResponseDataModel findWardByIdApi1(@PathVariable("id") String id) {
		return wardService.findWardByIdApi(id);
	}
}
