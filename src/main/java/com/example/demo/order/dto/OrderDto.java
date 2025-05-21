package com.example.demo.order.dto;

import java.time.LocalDateTime;
import java.util.List;


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
