package com.example.demo.orderItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.orderItem.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	
	
}
