package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.UserService;

@Controller
public class HomeController	 {
	
	@Autowired
	UserService service;
	
	// 테스트 용도
	// 잘 나옴
    @GetMapping("/")
    public String home() {
        return "home/main";
    }
    
    @GetMapping("/customlogin")
    public String customLogin() {
    	// 로그인 html 만들기
    	return "home/login";
    }
    
}