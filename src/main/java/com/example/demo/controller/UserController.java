package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.config.SecurityConfig;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

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
			System.out.println("값이 들어옴");
			return "redirect:/";
		}
		else {
			System.out.println("실패함");
			redirectAttributes.addFlashAttribute("msg", "아이디가 중복되어 등록에 실패");
			return "redirect:/user/register";
		}
	}
	
	
}
