package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	@Test
	void 값넣기() {
		UserDto user = UserDto.builder()
				.userName("홍길동")
				.password("1234")
				.role("관리자")
				.build();
		
		service.register(user);
	}
	
	@Test
	void 찾기() {
		service.read("홍길동");
	}
	
}
