package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.cart.dto.CartDto;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cart.service.CartService;

@SpringBootTest
public class CartServiceTest {
	
	@Autowired
	CartRepository repository;
	
	@Autowired
	CartService service;
	
	@Test
	void 서비스확인_및_레포지토리확인() {
		System.out.println("서비스 : " + service);
		System.out.println("리포지토리 : " + repository);
	}
	
	@Test
	void 데이터_추가하기() {
		// 물건 아이디와 유저이름은 꼭 DB에 있는걸 해야함
		CartDto dto = CartDto.builder()
				.productsid(6)
				.user("둘리")
				.build();
		int cartnum = service.register(dto);
		System.out.println(cartnum);
	}
	
	
	@Test
	void 데이터제거하기() {
		// 지우는건 카트
		boolean testremove = service.remove(1);
		if(testremove) {
			System.out.println("성공적으로 삭제됨");
		}
		else {
			System.out.println("안지워짐");
		}
	}
}











