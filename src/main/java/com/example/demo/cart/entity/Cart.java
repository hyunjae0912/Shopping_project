package com.example.demo.cart.entity;

import com.example.demo.products.entity.Products;
import com.example.demo.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "tbl_cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cartId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	Products products;
	
	@ManyToOne
    @JoinColumn(name = "userName")
	User user;
	
}
