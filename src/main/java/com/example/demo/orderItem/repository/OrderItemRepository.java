package com.example.demo.orderItem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.orderItem.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	
	List<OrderItem> findByOrder_User_UserName(String userName);
	
}
