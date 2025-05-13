package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "tbl_user")
public class User {
	
	@Id
	@Column(length = 100, nullable = false)
	String userName;
	
	@Column(length = 20, nullable = false)
	String role;

	@Column(length = 100, nullable = false)
	String password;
	
}
