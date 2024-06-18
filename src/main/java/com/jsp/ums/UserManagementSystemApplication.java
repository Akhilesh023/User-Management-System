package com.jsp.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jsp.ums.controller.UserController;
import com.jsp.ums.entity.User;

@SpringBootApplication
public class UserManagementSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(UserManagementSystemApplication.class, args);
		UserController userController = applicationContext.getBean(UserController.class);
//		User user = new User();
//		userController.addUser(user);
		
	}

}
