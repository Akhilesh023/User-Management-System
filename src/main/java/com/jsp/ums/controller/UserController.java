package com.jsp.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Endpoints", description = "Contains all the end points that are related to user entity.")//api doc
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@Operation(description = "This operation is used to add the data to the database"
			, responses = {
					@ApiResponse(responseCode = "201", description = "User Created" ),
					@ApiResponse(responseCode = "400", description = "Invalid Input" ,
					content = {
							@Content(schema = @Schema(oneOf = ResponseStructure.class))
					})
			})// api doc
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest userRequest){
		return userService.addUser(userRequest);
	}
	@GetMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(@PathVariable int userId){
		return userService.findByUserId(userId);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<User>> updateByUserId(@RequestBody User user,@PathVariable int userId){
		return userService.updateByUserId(userId,user);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(@PathVariable int userId){
		return userService.deleteByUserId(userId);
	}
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll(){
		return userService.findAll();
	}

}
