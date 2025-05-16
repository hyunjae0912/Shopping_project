package com.example.demo.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.order.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer>{
//	List<Order> findByUser(String user);
//	@Modifying
//	@Query("select from ")
}
