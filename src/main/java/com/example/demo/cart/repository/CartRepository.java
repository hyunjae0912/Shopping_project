package com.example.demo.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
}
