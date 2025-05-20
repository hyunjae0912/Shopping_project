package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// C:\\uploadfile\\ 라는 경로를 웹루트로 연결하기 위한 클래스

// 일반 클래스보다 먼저 실행됨
//@Configuration
public class WebConfig {
	// 외부 리소스
//	String pathImg = "file:/C:\\project_shoppting\\imgUrl";
//	
//	String pathDes = "file:/C:\\project_shoppting\\desUrl";
//	// 외부 리소스 경로를 웹루트로 매핑하는 함수
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		
//		// 외부 리소스 : c:\\uploadfile\\
//		// 웹루트 : 직접설정 (ex : upload)
//		
//		// 웹루트 경로 : 자유롭게 설정 가능
//		// 일반 이미지
//		registry.addResourceHandler("/img/**")
//		.addResourceLocations(pathImg);
//		
//		// 설명 이미지
//		registry.addResourceHandler("/des/**")
//		.addResourceLocations(pathDes);
//			
//		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	
	
