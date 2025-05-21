package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.orderItem.service.OrderItemService;

@SpringBootTest
public class OrderItemServiceTest {
	
	@Autowired
	OrderItemService service;
	
	@Test
	void 데이터가잘저장되나확인() {
		// 데이터 가독성이 너무 떨어지지만 잘 저장되긴 함
		service.register("홍길동", 8);
	}
	
}
 	