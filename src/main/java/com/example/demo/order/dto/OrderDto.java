package com.example.demo.order.dto;

import java.time.LocalDateTime;

import com.example.demo.cart.entity.Cart;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderDto {
	
	int orderId;
	
	LocalDateTime orderDate;
	
	String addr;
	
	String status;
	
	int totalPrice;
	
	String user;
}
