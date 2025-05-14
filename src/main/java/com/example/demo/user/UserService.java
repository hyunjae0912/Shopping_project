package com.example.demo.user;

public interface UserService {
	
	// 등록
	boolean register(UserDto dto);
	
	// 읽기
	UserDto read(String id);
	// 일단 등록만 만들어두고 생각하기
	
	default UserDto entityToDTO(User user) {
		UserDto dto = UserDto.builder()
				.userName(user.getUserName())
				.role(user.getRole())
				.password(user.getPassword())
				.build();
		
		return dto;
	}
	
	default User dtoToEntity(UserDto dto) {
		User user = User.builder()
				.userName(dto.getUserName())
				.role(dto.getRole())
				.password(dto.getPassword())
				.build();
		
		return user;
	}
	
}
