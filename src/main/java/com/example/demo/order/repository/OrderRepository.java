package com.example.demo.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.order.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer>{	
    @Query("SELECT o FROM Order o WHERE o.user.userName = :username")
    List<Order> findOrdersByUsername(@Param("username") String username);
    
}
