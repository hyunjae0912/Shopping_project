package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.user.UserDto;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	@Test
	void 값넣기() {
		UserDto user = UserDto.builder()
				.userName("둘리")
				.password("1234")
				.role("구매자")
				.build();
		
		UserDto user2 = UserDto.builder()
				.userName("홍길동")
				.password("4567")
				.role("판매자")
				.build();
		
		service.register(user);
		service.register(user2);
	}
	
	@Test
	void 찾기() {
		service.read("홍길동");
	}
	
}
