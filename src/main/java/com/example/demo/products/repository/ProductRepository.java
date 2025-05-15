package com.example.demo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.products.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{
	
}
