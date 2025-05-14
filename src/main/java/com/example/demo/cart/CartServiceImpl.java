package com.example.demo.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository repository;
	
	@Override
	public int register(CartDto dto) {
		System.out.println(dto);
		
		Cart entity = dtoToEntity(dto);
		repository.save(entity);
		int cartnum = entity.getCartId();
		
		System.out.println(entity);
		System.out.println(cartnum);
		
		return cartnum;
	}

	@Override
	public boolean remove(int no) {
		Optional<Cart> result = repository.findById(no);
		
		if(result.isPresent()) {
			repository.deleteById(no);
			return true;
		}
		
		return false;
	}
	
	
}
