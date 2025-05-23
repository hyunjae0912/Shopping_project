package com.example.demo.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.products.dto.ProductsDto;
import com.example.demo.products.entity.Products;
import com.example.demo.products.repository.ProductRepository;
import com.example.demo.user.repository.UserRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public int register(ProductsDto dto) {
		System.out.println(dto);
		
		Products entity = DtoToEntity(dto);
		productRepository.save(entity);
		int newName = entity.getProductid();
		
		System.out.println(entity);
		System.out.println(newName);
		
		return newName;
	}

	
	
	@Override
	public boolean remove(int no) {
		Optional<Products> result = productRepository.findById(no);
		
		if(result.isPresent()) {
			productRepository.deleteById(no);
			return true;
		}
		return false;
	}

	
	@Override
	public List<ProductsDto> getList() {
		List<Products> result = productRepository.findAll();
		List<ProductsDto> list = new ArrayList<>();
		
		list = result.stream()
				.map(entity -> EntityToDto(entity))
				.collect(Collectors.toList());
		
		return list;
	}
	
	
	@Override
	public void modify(ProductsDto dto) {
		
		// 파일은 바뀌었지만 DB에 저장이 안된다.
		Optional<Products> result = productRepository.findById(dto.getProductid());
		
		// result = oprional.empty
		System.out.println(result);
		
		if(result.isPresent()) {
			Products entity = result.get();
			
			entity.setImgUrl(dto.getImgUrl());
			entity.setDesImg(dto.getDesImg());;
			entity.setPrice(dto.getPrice());
			entity.setName(dto.getName());
			
			System.out.println(entity);
			
			productRepository.save(entity);
			
		}
		
	}

	
	
	@Override
	public ProductsDto read(int no) {
		// 자세히 보기
		Optional<Products> optional = productRepository.findById(no);
		
		if(optional.isPresent()) {
			Products products = optional.get();
			ProductsDto dto = EntityToDto(products);
			
			return dto;
		}
		else {
			return null;
		}
		
	}
	
}
