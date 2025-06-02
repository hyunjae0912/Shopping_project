package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.products.dto.ProductsDto;
import com.example.demo.products.service.ProductService;

@SpringBootTest
public class ProductServiceTest {
	@Autowired
	ProductService service;
	
	@Test
	void 서비스확인() {
		System.out.println("서비스 : " + service);
	}
	
	@Test
	void 값_저장() {
		// 값은 나중에ㅇㅇ;;
		// 무조건 DB에 저장된 이름을 사용해야만 저장이 됨.
		ProductsDto dto = ProductsDto.builder()
				.name("탄산수")
				.price(9000)
				.imgUrl("img2/탄산수.jpg")
				.desImg("설명 url")
				.user("고길동")
				.build();
		
		service.register(dto);
	}
	
}
