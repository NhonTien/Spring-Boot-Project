package com.training.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.service.StatsService;

@Controller
@RequestMapping(value = { "/admin/stats" })
public class StatsController {
	
	@Autowired
	StatsService statsService;
	
	@GetMapping
	public String categoryStats(Model model) {
		model.addAttribute("categoryStats", statsService.categoryStats());
		return "category-stats";
	}
	
	@GetMapping(value = { "/brand-stats" })
	public String brandStats(Model model) {
		model.addAttribute("brandStats", statsService.brandStats());
		return "brand-stats";
	}
	
	@GetMapping(value = { "/product-stats" })
	public String productStats(Model model, @RequestParam(required = false) Map<String, String> params) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String keyword = params.getOrDefault("keyword", null);
		
		Date fromDate = null;
		Date toDate = null;
		try {
			String from = params.getOrDefault("fromDate", null);
			if(from != null) {
				fromDate = format.parse(from);
			}
			
			String to = params.getOrDefault("toDate", null);
			if(to != null) {
				toDate = format.parse(to);
			}
		} catch (ParseException ex) {
			// TODO: handle exception
			//ex.printStackTrace();
		}
		model.addAttribute("productStats", statsService.productStats(keyword, fromDate, toDate));
		return "product-stats";
	}
	
	@GetMapping(value = { "/month-statss/{kw}/{from}/{to}" })
	@ResponseBody
	public List<Object[]> productMonthStatss(Model model, @PathVariable(name = "kw") String kw, @PathVariable("from") String from, @PathVariable("to") String to) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = f.parse(from);
		Date toDate = f.parse(to);
		return statsService.productMonthStats(null, fromDate, toDate);
	}
	
	@GetMapping(value = { "/month-statss/findAll" })
	@ResponseBody
	public List<Object[]> productMonthStatss1() {
		return statsService.productMonthStats(null, null, null);
	}
	
	@GetMapping(value = { "/month-stats" })
	public String productStatss(Model model, @RequestParam(required = false) Map<String, String> params) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String keyword = params.getOrDefault("keyword", null);
		Date fromDate = null;
		Date toDate = null;
		try {
			String from = params.getOrDefault("fromDate", null);
			if(from != null) {
				fromDate = f.parse(from);
			} else {
				fromDate = null;
			}
			
			String to = params.getOrDefault("toDate", null);
			if(to != null) {
				toDate = f.parse(to);
			} else {
				toDate = null;
			}
		} catch (ParseException ex) {
			// TODO: handle exception
			//ex.printStackTrace();
		}
		model.addAttribute("productMonthStats", statsService.productMonthStats(keyword, fromDate, toDate));
		return "month-stats";
	}
	
	@GetMapping(value = { "/comment-stats" })
	public String commentStats(Model model) {
		model.addAttribute("commentStats", statsService.commentStats());
		return "comment-stats";
	}
	
	@PostMapping(value = { "/hotProducts/{categoryId}/{number}" })
	@ResponseBody
	public List<Object[]> hotProducts(@PathVariable("categoryId") Long categoryId, @PathVariable("number") int number) {
		return statsService.hotProducts(categoryId, number);
	}

}
