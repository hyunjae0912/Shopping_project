package com.example.demo.orderItem.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.order.entity.Order;
import com.example.demo.products.entity.Products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter	
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = { AuditingEntityListener.class }) 
@Table(name = "tbl_orderitem")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderItemId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	Order order;
	
	@Column(length = 50)
	String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	Products products;
}