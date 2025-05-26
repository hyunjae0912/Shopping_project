package com.example.demo.products.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsDto {
	int productid;
	
	String name;
	
	int price;
	
	int count;
	
	String imgUrl;
	
	String desImg;
	
	// User user 이다 
	String user;
}
