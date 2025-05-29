package com.example.demo.orderItem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderItemDto {
	
	int orderItemId;
	
	String status;
	
	int order;
	
	int products;
	
}
