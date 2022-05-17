package com.training.controller;

import java.security.Principal;
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

import com.training.entity.UserEntity;
import com.training.model.ResponseDataModel;
import com.training.security.UserService;

@Controller
@RequestMapping(value = { "admin/user" })
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping(value = { "account-admin" })
	public String initPage() {
		return "useradmin-index";
	}
	
	@GetMapping(value = { "account-user" })
	public String initPage1() {
		return "user-index";
	}
	
	@PostMapping(value = {"/api/add"})
	@ResponseBody
	public ResponseDataModel addAdminApi(@ModelAttribute UserEntity userEntity) {
		return userService.addAminApi(userEntity);
	}
	
	@PostMapping(value = {"/api/changePassword"})
	@ResponseBody
	public ResponseDataModel changePasswordApi(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword, Principal principal) {
		return userService.changePasswordApi(currentPassword, newPassword, confirmPassword, principal);
	}
	
	@DeleteMapping(value = {"/api/delete/{userId}"})
	@ResponseBody
	public ResponseDataModel deleteApi(@PathVariable("userId") Long userId) {
		return userService.deleteApi(userId);
	}
	
	@PostMapping(value = {"/api/search/{pageNumber}"})
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions, @PathVariable("pageNumber") int pageNumber) {
		return userService.searchWithConditions(searchConditions, pageNumber);	
	}
}
