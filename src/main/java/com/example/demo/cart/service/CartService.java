package com.example.demo.cart.service;

import java.util.List;

import com.example.demo.cart.dto.CartDto;
import com.example.demo.cart.entity.Cart;
import com.example.demo.products.entity.Products;
import com.example.demo.user.entity.User;

public interface CartService {
	
	// 등록
	int register(CartDto dto);
	
	// 지우기
	boolean remove(int no);
	
	// 이름찾아 표시하기
	List<CartDto> read(String userName);
	
	default CartDto entityToDto(Cart cart) {
		String user = cart.getUser().getUserName();
		int product = cart.getProducts().getProductid();
		
		CartDto dto = CartDto.builder()
				.cartId(cart.getCartId())
				.user(user)
				.productsid(product)
				.build();
		
		return dto;
	}
	
	default Cart dtoToEntity(CartDto dto) {
		
		User user = User.builder()
				.userName(dto.getUser()).build();
		
		Products products = Products.builder()
				.productid(dto.getProductsid()).build();
		
		Cart cart = Cart
				.builder()
				.cartId(dto.getCartId())
				.products(products)
				.user(user)
				.build();
		
		return cart;
	}
	
	
}
