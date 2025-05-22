package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.orderItem.entity.OrderItem;
import com.example.demo.orderItem.repository.OrderItemRepository;

@SpringBootTest
public class OrderItemRepositoryTest {
	
	@Autowired
	OrderItemRepository repository;
	@Test
	void 기능실행() {
		List<OrderItem> list = repository.findByOrder_User_UserName("홍길동");
		
		
		for(OrderItem oi : list) {
		    System.out.println("OrderItemId: " + oi.getOrderItemId() +
		                       ", OrderId: " + oi.getOrder().getOrderId() +
		                       ", ProductId: " + oi.getProducts().getProductid());
		}

		
	}
}
