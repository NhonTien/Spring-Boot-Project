package com.training.controller;

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

import com.training.entity.CommentEntity;
import com.training.model.ResponseDataModel;
import com.training.service.ICommentService;

@Controller
@RequestMapping(value = { "admin/comment" })
public class CommentController {

	@Autowired
	ICommentService commentService;
	
	@GetMapping
	public String initPage() {
		return "comment-index";
	}
	
	@GetMapping("/api/find/{productId}")
	@ResponseBody
	public ResponseDataModel findAllByCommentIdApi(@PathVariable("productId") Long productId) {
		return commentService.findAllCommentByIdApi(productId);
	}
	
	@GetMapping("/api/find")
	@ResponseBody
	public ResponseDataModel findAllByCommentIdApi1(@RequestParam("productId") Long productId) {
		return commentService.findAllCommentByIdApi(productId);
	}
	
	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addApi(@ModelAttribute CommentEntity commentEntity) {
		return commentService.addApi(commentEntity);
	}
	
	@DeleteMapping(value = {"/api/delete/{commentId}"})
	@ResponseBody
	public ResponseDataModel deleteApi(@PathVariable("commentId") Long commentId) {
		return commentService.deleteApi(commentId);
	}
	
	@GetMapping("/api/findAll/{productId}/{pageNumber}")
	@ResponseBody
	public ResponseDataModel findAllWithPagerApi(@PathVariable("productId") Long productId, @PathVariable("pageNumber") int pageNumber) {
		return commentService.findAllWithPagerApi(productId, pageNumber);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return commentService.searchWithConditions(searchConditions, pageNumber);	
	}
	
}
