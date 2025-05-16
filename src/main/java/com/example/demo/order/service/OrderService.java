package com.example.demo.order.service;

import java.util.List;

import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.entity.Order;
import com.example.demo.user.entity.User;

public interface OrderService {
	
	// 주문번호 저장
	int register(OrderDto dto);
	
	// 주문 번호를 통해서 받아오기
	OrderDto read(int no);
	
	// 주문 목록 표시 (관리자 입장)
	List<OrderDto> getAllList();
	
	// 주문 목록 표시 (구매자 입장)
	List<OrderDto> getUserList(String username);
	
	// 상태 업데이트
	void updateStatus(int orderId, String status);
	
	
	default OrderDto entityToDto(Order order) {
		
		String user = order.getUser().getUserName();
		
		OrderDto dto = OrderDto
				.builder()
				.orderId(order.getOrderId())
				.orderDate(order.getOrderDate())
				.totalPrice(order.getTotalPrice())
				.user(user)
				.addr(order.getAddr())
				.status(order.getStatus())
				.build();
		
		return dto;
	}
	
	default Order dtoToEntity(OrderDto dto) {
		User user = User.builder()
				.userName(dto.getUser()).build();
		
		Order order = Order
				.builder()
				.orderId(dto.getOrderId())
				.orderDate(dto.getOrderDate())
				.totalPrice(dto.getTotalPrice())
				.user(user)
				.addr(dto.getAddr())
				.status(dto.getStatus())
				.build();
		
		return order;
	}
	
	
}
