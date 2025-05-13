package com.example.demo.dto;

import java.util.Arrays;


public class CustomerUser{
}


/*
 * package com.example.demo.dto;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomerUser extends User{
	
	public CustomerUser(UserDto dto) {
		super(dto.getUserName(), dto.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));
	}
}

 */