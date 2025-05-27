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
import com.example.demo.order.controller.OrderController;
import com.example.demo.products.dto.ProductsDto;
import com.example.demo.products.entity.Products;
import com.example.demo.products.repository.ProductRepository;
import com.example.demo.products.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

	@GetMapping("/cart")
	public void cart(Model model, Principal principal) {
		
		String name = principal.getName();
		
		
		List<CartDto> list = cartService.read(name);
		int total = 0;
		
		// 물건의 총 합 더하기
		for(CartDto dto : list) {
			int dtonum = dto.getProducts().getPrice();
			total += dtonum;
		}
		
		System.out.println(total);
		model.addAttribute("userName", name); 	
		model.addAttribute("list" ,list);
		model.addAttribute("total", total);
	}
	
	
	@PostMapping("/cart")
	public String addCart(@RequestParam("productId") int productId, Model model
			,Principal principal) {
		
	    String userName = principal.getName();
	    
	    
	    int num = productService.discount(productId);
	    
	    if(num == 0) {
	    	model.addAttribute("msg", "물건이 없습니다.");
	    	return "/cart/cart";  	
	    }
	    
	    Products products = productRepository.findById(productId).orElse(null);
	    
	    CartDto dto = CartDto.builder()
	            .productsid(productId)
	            .user(userName)
	            .products(products)
	            .build();
	    
	    int cartnum = cartService.register(dto);
	    
	    System.out.println(cartnum);
	    
	    model.addAttribute("msg", "장바구니 추가됨");
	    
	    return "redirect:/cart/cart"; // 장바구니 페이지로 이동
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("productid") int productid) {
		// 상품 카운트는 증가시키고
		productService.countUp(productid);
		// 장바구니 값은 지운다
		cartService.remove(productid);		
		return "redirect:/cart/cart";
	}
	
}