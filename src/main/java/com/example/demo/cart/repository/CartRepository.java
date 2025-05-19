package com.example.demo.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.cart.entity.Cart;
import com.example.demo.user.entity.User;


public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	// 유저 기준으로 장바구니 리스트 조회
	List<Cart> findByUser(User user);
	
    List<Cart> findByUser_UserName(String userName);  
    // "user"는 Cart 엔티티에서 User와 연결된 필드명
	
}
