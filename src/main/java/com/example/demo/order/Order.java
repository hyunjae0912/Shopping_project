package com.example.demo.order;

import java.time.LocalDateTime;

import com.example.demo.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter	
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "tbl_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;
	
	LocalDateTime orderDate;
	
	int totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "username")
	User user;
}
