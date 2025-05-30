	package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.config.SecurityConfig;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/register")
	public String register() {
		return "/user/register";
	}
	
	@PostMapping("/register")
	public String registerPost(UserDto dto, RedirectAttributes redirectAttributes) {
		boolean isSuccess = service.register(dto);
		
		if(isSuccess) {
			System.out.println("회원가입 성공");
			return "redirect:/home/login";
		}
		else {
			System.out.println("실패함");
			
			return "redirect:/register";
		}
	}
	
	
}
