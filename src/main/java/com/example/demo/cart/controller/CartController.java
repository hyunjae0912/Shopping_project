package com.example.demo.cart.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.dto.CartDto;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cart.service.CartService;
import com.example.demo.products.entity.Products;
import com.example.demo.products.repository.ProductRepository;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/cart")
	public void cart(Model model) {
		//String name = principal.getName();
		String userName = "둘리";
		
		List<CartDto> list = cartService.read(userName);
		int total = 0;
		
		// 물건의 총 합 더하기
		for(CartDto dto : list) {
			int dtonum = dto.getProducts().getPrice();
			total += dtonum;
		}
		System.out.println(total);
		model.addAttribute("list" ,list);
		model.addAttribute("total", total);
	}
	
	// Principal principal
	@PostMapping("/cart")
	public String addCart(@RequestParam("productId") int productId) {
		
	    String userName = "둘리";
	    
	    Products products = productRepository.findById(productId).orElse(null);
	    
	    System.out.println(productId);
	    System.out.println(products);
	    
	    CartDto dto = CartDto.builder()
	            .productsid(productId)
	            .user(userName)
	            .products(products)
	            .build();
	    		
	    
	    int cartnum = cartService.register(dto);
	    
	    System.out.println(cartnum);
	    
	    return "redirect:/cart/cart"; // 장바구니 페이지로 이동
	}
	
}

