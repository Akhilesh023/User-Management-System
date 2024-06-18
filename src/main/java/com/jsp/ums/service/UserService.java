package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int userId);

	ResponseEntity<ResponseStructure<User>> updateByUserId(int userId, User user);

	ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(int userId);

	ResponseEntity<ResponseStructure<List<UserResponse>>> findAll();

}
