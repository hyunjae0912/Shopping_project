package com.example.demo.service;

import static org.mockito.Mockito.lenient;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.order.service.OrderService;

@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
	OrderService service;
	
	@Autowired
	OrderRepository repository;
	
	
	@Test
	void 데이터추가() {
		
		// 시간까지 잘 추가됨
		OrderDto dto = OrderDto.builder()
				.addr("인천광역시 남동구")
				.status("주문 확인중")
				.totalPrice(20000)
				.user("홍길동").build();
		
		service.register(dto);
	}
	
	@Test
	void 데이터읽기() {
		OrderDto dto = service.read(3);
		
		System.out.println(dto);
		
	}
	
	@Test
	void 전부찾기() {
		System.out.println(service.getAllList());
	}
	
	@Test
	void 상태업데이트() {
		service.updateStatus(2, "배송완료");
		
	}
	
	@Test
	void 유저이름겟() {
		List<OrderDto> list = service.getUserList("홍길동");
		
		
		System.out.println(list);
	}
	
}


































