package com.example.demo.orderItem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.orderItem.entity.OrderItem;
import com.example.demo.orderItem.repository.OrderItemRepository;
import com.example.demo.orderItem.service.OrderItemService;

// 마이페이지처럼 사용할 예정

// /mypage/main

@Controller
@RequestMapping("/mypage")
public class OrderItemController {
	
	@Autowired
	OrderItemService service;
	
	// Principal principal
	@GetMapping("/main")
	public void mypage(Model model, Principal principal) {
		String name = principal.getName();
		
		List<OrderItem> list = service.read(name);
		
		model.addAttribute("name", name);
		model.addAttribute("list", list);
		
		// th:src="@{'/upload/' + ${dto.imgFileName}}"
	}
	
	
}
  