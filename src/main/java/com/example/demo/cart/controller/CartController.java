package com.example.demo.cart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.dto.CartDto;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cart.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/cart")
	public void cart() {
		
	}
	
	// Principal principal
	@PostMapping("/cart")
	public String addCart(@RequestParam("productId") int productId) {
		
	    String userName = "둘리";
	    
	    CartDto dto = CartDto.builder()
	            .productsid(productId)
	            .user(userName)
	            .build();
	    		
	    
	    int cartnum = cartService.register(dto);
	    
	    System.out.println(cartnum);
	    
	    return "redirect:/cart/cart"; // 장바구니 페이지로 이동
	}
	
}

