package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// C:\\uploadfile\\ 라는 경로를 웹루트로 연결하기 위한 클래스

// 일반 클래스보다 먼저 실행됨
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/uploadImg/**").addResourceLocations("file:/C:/project_shoppting/imgUrl/");
		registry.addResourceHandler("/uploadDes/**").addResourceLocations("file:/C:/project_shoppting/desUrl/");

		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
}