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
	
	@GetMapping("/cart")
	public void cart() {
		
	}
	
	@PostMapping("/add")
	public String addCart(@RequestParam("productId") int productId) {
		
	    String userName = "둘리";
	    
	    CartDto dto = CartDto.builder()
	            .productsid(productId)
	            .user(userName)
	            .build();
	    		
	    
	    int cartnum = cartService.register(dto);
	    
	    System.out.println(cartnum);
	    
	    // 일단 페이지로 이동하지않고 400에러가 나서
	    // 내일 (05-15) 해결하기
	    
	    return "redirect:/cart/cart"; // 장바구니 페이지로 이동
	}
	
}

