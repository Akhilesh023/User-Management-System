package com.jsp.ums.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ums.exception.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationHandler  {
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<Map<String, String>>>  handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		
		List<ObjectError> errors = ex.getAllErrors(); //getting all errors
		
//		List<String> allErrors = errors.stream().map(error -> {
//			FieldError fieldError = (FieldError)error; 
//			return fieldError.getField()+" : "+fieldError.getDefaultMessage(); 
//			
//			//down-casting to access child members
//			   //getDefaultMessage is from fieldError and
//				//ObjectError is parent class.
//		
//		}).toList();  //converting the accessed data into a list.
		
		Map<String, String> allErrors = new HashMap<String,String>();
		
		errors.forEach(error -> {
			FieldError fieldError = (FieldError)error;
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			allErrors.put(field, message); 
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseStructure<Map<String, String>>()
						.setStatus(HttpStatus.BAD_REQUEST.value())
						.setMessage("Invalid output")
						.setData(allErrors));
	}
	
	
	
	
	
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFoundByIdException(UserNotFoundByIdException e){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(HttpStatus.NOT_FOUND.name());
		rs.setData("User not found for the requested ID");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}

}
