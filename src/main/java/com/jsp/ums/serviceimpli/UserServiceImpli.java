package com.jsp.ums.serviceimpli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.exception.UserNotFoundByIdException;
import com.jsp.ums.mapper.UserMapper;
import com.jsp.ums.repository.UserRepository;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

import lombok.Builder;

@Service
public class UserServiceImpli implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {
		
		//User Request
//		User user = User.builder()
//		.username(userRequest.getUsername())
//		.email(userRequest.getEmail())
//		.password(userRequest.getPassword())
//		.build();
//		
//		user = userRepository.save(user);
		
		User user = userRepository.save(userMapper.mapToUser(userRequest, new User()));
		
		//User Response
		
//		UserResponse response = UserResponse.builder()
//		.userId(user.getUserId())
//		.username(user.getUsername())
//		.email(user.getEmail())
//		.build();
		
		
//		return ResponseEntity.status(HttpStatus.CREATED)
//		.body(new ResponseStructure<UserResponse>().setStatus(HttpStatus.CREATED.value())
//				.setMessage("Data Inserted Successfully").setData(response));
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<UserResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage(HttpStatus.CREATED.name())
						.setData(userMapper.mapToUserResponse(user)));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int userId) {	
		
		
		 return userRepository.findById(userId).map(user -> {
//			ResponseStructure<User> rs = new ResponseStructure<User>();
//			rs.setStatus(HttpStatus.FOUND.value());
//			rs.setMessage(HttpStatus.FOUND.name());
//			rs.setData(user);
//			return ResponseEntity.status(HttpStatus.FOUND).body(rs);
			 
			 UserResponse userResponse = userMapper.mapToUserResponse(user);
			 return ResponseEntity.status(HttpStatus.FOUND)
					 .body(new ResponseStructure<UserResponse>()
							 .setData(userResponse)
							 .setMessage("User Found Successfully")
							 .setStatus(HttpStatus.FOUND.value()));
			 
		}).orElseThrow(() -> new UserNotFoundByIdException("User Not Found for the ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> updateByUserId(int userId, User user) {
		
		return userRepository.findById(userId).map(exUser -> {
			exUser.setUsername(user.getUsername());
			exUser.setEmail(user.getEmail());
			exUser.setPassword(user.getPassword());
			
			exUser = userRepository.save(exUser);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<User>()
							.setMessage("User Updated")
							.setData(exUser));
		}).orElseThrow(() -> new UserNotFoundByIdException("Failed to Update the user"));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(int userId) {
		
		return userRepository.findById(userId).map(user -> {
			
			userRepository.delete(user);
			
//			ResponseStructure<User> rs = new ResponseStructure<User>();
//			rs.setStatus(HttpStatus.OK.value());
//			rs.setMessage(HttpStatus.OK.name());
//			rs.setData(user);
//			return ResponseEntity.status(HttpStatus.OK).body(rs);
			
			UserResponse userResponse = userMapper.mapToUserResponse(user);
			 return ResponseEntity.status(HttpStatus.OK)
					 .body(new ResponseStructure<UserResponse>()
							 .setData(userResponse)
							 .setMessage("User Deleted Successfully")
							 .setStatus(HttpStatus.OK.value()));
			
			
		}).orElseThrow(() -> new UserNotFoundByIdException("Failed to delete the User"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll() {
		
//		List<User> all = userRepository.fi	ndAll();
		
		List<UserResponse> userResponses = userRepository.findAll().stream().map( user -> userMapper
				.mapToUserResponse(user))
				.toList();
		
				
		
//		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<User>>().setStatus(HttpStatus.OK.value()).
//				setMessage(HttpStatus.OK.name()).setData(all));
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<UserResponse>>()
						.setData(userResponses)
						.setMessage("Users Found")
						.setStatus(HttpStatus.FOUND.value()));
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
