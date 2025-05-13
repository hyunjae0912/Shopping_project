package com.example.demo.entity;



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
@Table(name = "tbl_products")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productid;
	
	@Column(length = 100, nullable = false)
	String name;
	
	@Column(length = 10)
	int price;
	
	
	@Column(length = 100, nullable = false)
	String imgUrl;
	
	@Column(length = 100, nullable = false)
	String desImg;
	
	// 닉네임
	// 상품 등록한 사람이 누군지 추적
	@ManyToOne
	@JoinColumn(name = "username")
	User user;
}
