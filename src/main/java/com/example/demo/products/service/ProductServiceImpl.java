package com.example.demo.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.products.dto.ProductsDto;
import com.example.demo.products.entity.Products;
import com.example.demo.products.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	
	@Autowired
	ProductRepository productRepository;

	
	@Autowired
	CartRepository cartRepository;

	
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
	public int discount(int productId) {
		
		try {
			Optional<Products> result = productRepository.findById(productId);
			
			System.out.println(result);
			int nowCount;
			
			if(result.isPresent()) {
				Products entity = result.get(); 
				nowCount = entity.getCount();
				if(nowCount > 0) {
					entity.setCount(nowCount - 1);
					System.out.println(entity.getCount());
					productRepository.save(entity);
					return entity.getCount();
				}
			}
			return 0;			
		}
		catch (Exception e) {
			System.out.println("error : " + e);
			return 0;
		}		
	}	
	
	
	@Override
	public int countUp(int productId) {
		try {
			// 카트에서 물건 값을 가져옴
			int realProductId = 0;
			Optional<Cart> resultCart = cartRepository.findById(productId);
			
			if(resultCart.isPresent()) {
				Cart entityCart = resultCart.get();
				realProductId = entityCart.getProducts().getProductid();				
			}
			// 가져온 물건 아이디를 이용해서 한번 더 찾으
			Optional<Products> result = productRepository.findById(realProductId);
			System.out.println(result);
			int nowCount;
			
			if(result.isPresent()) {
				Products entity = result.get();
				nowCount = entity.getCount();
				entity.setCount(nowCount + 1);
				productRepository.save(entity);
				return entity.getCount();
			}
			return 0;	
		}
		catch (Exception e) {
			System.out.println("error : " + e);
			return 0;
		}
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
			entity.setCount(dto.getCount());
			
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