package com.example.demo.orderItem.service;

import java.util.List;

import com.example.demo.order.entity.Order;
import com.example.demo.orderItem.dto.OrderItemDto;
import com.example.demo.orderItem.entity.OrderItem;
import com.example.demo.products.entity.Products;

public interface OrderItemService {
	
	// 저장
	List<OrderItemDto> register(String userName, int orderId);
	
	OrderItemDto read(int no);
	
	
	default OrderItem dtoToEntity(OrderItemDto dto) {
		
		Order order = Order.builder()
				.orderId(dto.getOrder()).build();
		
		Products products = Products.builder()
				.productid(dto.getProducts()).build();
		
		OrderItem orderItem = OrderItem
				.builder()
				.order(order)
				.products(products)
				.build();
		
		return orderItem;
	}
	
	default OrderItemDto entityToDto(OrderItem orderItem) {
		
		int order = orderItem.getOrder().getOrderId();
		int products = orderItem.getProducts().getProductid();
		
		OrderItemDto dto = OrderItemDto
				.builder()
				.order(order)
				.products(products)
				.build();
		
		return dto;
	}
	
}
