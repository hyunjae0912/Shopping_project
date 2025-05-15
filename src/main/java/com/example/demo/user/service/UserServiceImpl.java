package com.example.demo.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	UserRepository repository;
	
	// 비밀번호 암호화
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@Override
	public boolean register(UserDto dto) {
		try {	
			String id = dto.getUserName();
			UserDto getDto = read(id);
			
			if(getDto != null) {
				System.out.println("사용중인 아이디입니다.");
				return false;
			}
			else {
				System.out.println("아이디 추가중");
				User entity = dtoToEntity(dto);
				
				// 비밀번호 암호화를 위해 가져옴
//				String password = entity.getPassword();
//				String enpw = passwordEncoder.encode(password);
//				
//				entity.setPassword(enpw);
				repository.save(entity);
				System.out.println("저장완료");
				return true;
			}
		} catch (Exception e) {
			System.out.println("error : " + e);
			return false;
		}

	}

	@Override
	public UserDto read(String id) {
		Optional<User> result = repository.findById(id);
		if(result.isPresent()) {
			User user = result.get();
			return entityToDTO(user);
		}
		else {
			return null;			
		}
	}
	
	

	
	
}
