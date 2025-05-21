package com.example.demo.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cart.dto.CartDto;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.user.controller.UserController;
import com.example.demo.user.entity.User;

@Service
public class CartServiceImpl implements CartService {

    private final UserController userController;
	
	@Autowired
	CartRepository repository;

    CartServiceImpl(UserController userController) {
        this.userController = userController;
    }
	
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
	public List<CartDto> read(String userName) {
		// 유저이름 엔티티 생성
		try {
			User user = User.builder()
					.userName(userName).build();
			
			List<Cart> entitylist = repository.findByUser(user);
			List<CartDto> dtoList = new ArrayList<>();
			
			for(Cart entity : entitylist) {
				CartDto dto = entityToDto(entity);
				dto.setProducts(entity.getProducts());
				dtoList.add(dto);
			}
			return dtoList;
		}
		catch (Exception error) {
			System.out.println("error : " + error);
			return null;
		}

	}
	
	@Override
	public boolean remove(int no) {
		// 물건 번호를 지우는게 아니라 장바구니 고유 번호를 지워야함
		System.out.println(no);
		Optional<Cart> result = repository.findById(no);
		
		if(result.isPresent()) {
			repository.deleteById(no);
			return true;
		}
		
		return false;
	}

	
	
	
	
}
