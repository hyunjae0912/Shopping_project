package com.example.demo.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.products.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{
	
	List<Products> findByNameContaining(String keyword);
	
}
