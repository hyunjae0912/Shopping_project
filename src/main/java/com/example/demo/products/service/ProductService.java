package com.example.demo.products.service;

import java.util.List;

import com.example.demo.products.dto.ProductsDto;
import com.example.demo.products.entity.Products;
import com.example.demo.user.entity.User;

public interface ProductService {
	
	// 물건 서비스
	
	// 물건 저장
	int register(ProductsDto dto);
	
	// 물건 삭제 product고유 번호를 사용해서 지우기
	boolean remove(int no);
	
	// 물건 표시
	List<ProductsDto> getList();
	
	// 물건 수정
	void modify(ProductsDto dto);
	
	// 물건 상세 조회
	ProductsDto read(int no);
	
	
	default Products DtoToEntity(ProductsDto dto) {
		
		User user = User.builder()
				.userName(dto.getUser()).build();
		
		Products products = Products.builder()
				.productid(dto.getProductid())
				.price(dto.getPrice())
				.name(dto.getName())
				.imgUrl(dto.getImgUrl())
				.desImg(dto.getDesImg())
				.user(user)
				.build();
		
		return products;
	}
	
	default ProductsDto EntityToDto(Products products) {
		
		String user = products.getUser().getUserName();
		
		ProductsDto dto = ProductsDto.builder()
				.productid(products.getProductid())
				.price(products.getPrice())
				.name(products.getName())
				.imgUrl(products.getImgUrl())
				.desImg(products.getDesImg())
				.user(user)
				.build();
		
		return dto;
		
	}
	
	
}
