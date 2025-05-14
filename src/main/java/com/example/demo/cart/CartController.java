package com.example.demo.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
	@GetMapping
	public String cart() {
	    return "cart/cart"; // templates/cart/cart.html
	}
	
	@PostMapping("/add")
	public String addCart(@RequestParam("productId") int productId) {
		
		
		
		return null;
	}
	
}

