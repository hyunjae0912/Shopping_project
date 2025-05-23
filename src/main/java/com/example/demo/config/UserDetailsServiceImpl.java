package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerUser;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("login id : " + username);
		
		UserDto dto = service.read(username);
			
		if(dto == null) {
			throw new UsernameNotFoundException(username);
		}
		else {
			return new CustomerUser(dto);
		}
	}
}