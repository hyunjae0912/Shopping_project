package com.example.demo.home;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.products.dto.ProductsDto;
import com.example.demo.products.service.ProductService;
import com.example.demo.user.service.UserService;

@Controller
public class HomeController	 {
	
	@Autowired
	UserService service;
	
	@Autowired
	ProductService productService;
	
    @GetMapping("/")
	public String list(Model model, Principal principal) {
		List<ProductsDto> list = productService.getList();
	    String name = (principal != null) ? principal.getName() : "게스트";
	    
	    System.out.println("이름 : " + name);
		model.addAttribute("list", list);
		model.addAttribute("name", name);
		return "products/list";
	}
    
    @GetMapping("/customlogin")
    public String customLogin() {
    	// 로그인 html 만들기
    	return "home/login";
    }
    
}