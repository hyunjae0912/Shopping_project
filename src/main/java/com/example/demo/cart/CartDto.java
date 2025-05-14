package com.example.demo.cart;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartDto {
	// 카트 고유 번호
	int cartId;
	
	// 물건 아이디(숫자로 되어있음) 받아옴
	int productsid;
	
	// 이 쇼핑몰을 이용하는 이용자 이름을 가져옴
	String user;
}
