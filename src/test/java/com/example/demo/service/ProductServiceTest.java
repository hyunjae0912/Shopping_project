package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.ProductsDto;

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
		// 무조건 DB에 저장된 이름을 사용해야만 저장이 됨.
		ProductsDto dto = ProductsDto.builder()
				.name("키위")
				.price(10000)
				.imgUrl("이미지url")
				.desImg("설명 url")
				.user("홍길동")
				.build();
		
		service.register(dto);
	}
	
}
