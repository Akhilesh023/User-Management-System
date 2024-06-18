package com.jsp.ums.mapper;

import org.springframework.stereotype.Component;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;

@Component
public class UserMapper {
	
	public User mapToUser(UserRequest userRequest, User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setPhNo(userRequest.getPhNo());
		return user;
	}
	
	public UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.phNo(user.getPhNo())
				.build();
		
				
	}

}
