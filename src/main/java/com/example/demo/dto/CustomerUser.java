package com.example.demo.dto;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.example.demo.user.dto.UserDto;

public class CustomerUser extends User{
	
	public CustomerUser(UserDto dto) {
		super(dto.getUserName(), dto.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));
	}
}