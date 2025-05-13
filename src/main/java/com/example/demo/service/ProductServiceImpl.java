package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.ShoppingProjectApplication;
import com.example.demo.dto.ProductsDto;
import com.example.demo.entity.Products;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private final ShoppingProjectApplication shoppingProjectApplication;
	
	@Autowired
	ProductRepository repository;

    ProductServiceImpl(ShoppingProjectApplication shoppingProjectApplication) {
        this.shoppingProjectApplication = shoppingProjectApplication;
    }
	
	// 이미지 파일을 어떻게 저장하지?
	@Override
	public int register(ProductsDto dto) {
		System.out.println(dto);
		
		Products entity = DtoToEntity(dto);
		repository.save(entity);
		int newName = entity.getProductid();
		
		System.out.println(entity);
		System.out.println(newName);
		
		return newName;
	}

	
	
	@Override
	public boolean remove(int no) {
		Optional<Products> result = repository.findById(no);
		
		if(result.isPresent()) {
			repository.deleteById(no);
			return true;
		}
		return false;
	}

	
	// 리스트 형태로 출력
	@Override
	public List<ProductsDto> getList(int pageNumber) {
		List<Products> result = repository.findAll();
		List<ProductsDto> list = new ArrayList<>();
		
		list = result.stream()
				.map(entity -> EntityToDto(entity))
				.collect(Collectors.toList());
		
		return list;
	}

	
	
	@Override
	public void modify(ProductsDto dto) {
		
		Optional<Products> result = repository.findById(dto.getProductid());
		
		if(result.isPresent()) {
			Products entity = result.get();
			
			entity.setImgUrl(dto.getImgUrl());
			entity.setImgUrl(dto.getImgUrl());
			entity.setPrice(dto.getPrice());
			
			repository.save(entity);
			
		}
		
	}

	
	
	@Override
	public ProductsDto read(int no) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
