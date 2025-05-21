package com.example.demo.orderItem.dto;

import com.example.demo.order.entity.Order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderItemDto {
	
	int orderItemId;
	
	int order;
	
	int products;
	
}
