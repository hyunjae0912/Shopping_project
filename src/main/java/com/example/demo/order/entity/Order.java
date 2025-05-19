package com.example.demo.order.entity;

import java.time.LocalDateTime;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter	
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EntityListeners(value = { AuditingEntityListener.class }) 
@Table(name = "tbl_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;
	
	@CreatedDate
	LocalDateTime orderDate;
	
	int totalPrice;
	
	@Column(length = 100, nullable = false)
	String addr;
	
	@Column(length = 20, nullable = false)
	String status;
	
	@ManyToOne
	@JoinColumn(name = "username")
	User user;
	
	
}
