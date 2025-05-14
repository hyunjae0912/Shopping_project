package com.example.demo.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.UserRepository;

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
		
		Optional<Products> result = productRepository.findById(dto.getProductid());
		
		if(result.isPresent()) {
			Products entity = result.get();
			
			entity.setImgUrl(dto.getImgUrl());
			entity.setImgUrl(dto.getImgUrl());
			entity.setPrice(dto.getPrice());
			
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
