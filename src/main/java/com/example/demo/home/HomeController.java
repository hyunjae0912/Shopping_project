package com.example.demo.home;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.products.ProductService;
import com.example.demo.products.ProductsDto;
import com.example.demo.user.UserService;

@Controller
public class HomeController	 {
	
	@Autowired
	UserService service;
	
	@Autowired
	ProductService productService;
	
	// 테스트 용도
	// 잘 나옴
    @GetMapping("/")
	public String list(Model model) {
		List<ProductsDto> list = productService.getList();
		
		model.addAttribute("list", list);
		return "products/list";
	}
    
    @GetMapping("/customlogin")
    public String customLogin() {
    	// 로그인 html 만들기
    	return "home/login";
    }
    
}