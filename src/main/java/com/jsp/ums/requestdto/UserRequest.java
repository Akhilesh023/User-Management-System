package com.jsp.ums.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	@NotNull(message = "username cannnot be null")
	@NotBlank(message = "username cannot be blank")
//	@NotEmpty(message = "username cannot be empty")
	private String username;
//	@NotNull(message = "Email cannot be null")
//	@NotBlank(message = "Email cannot be blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
//	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
//			+ " contain at least one letter, one number, one special character")
	@NotNull(message = "Password cannot be null")
	@NotBlank(message = "Password cannot be blank")
	private String password;
	@Min(6000000000l)
	@Max(9999999999l)
	private long phNo;
	

}
