package com.jsp.ums.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDoc {
	
	// http://localhost:8080/swagger-ui.html
	
	@Bean
	public OpenAPI openAPI() {
	return new OpenAPI().info(new Info()
				.title("User Management System")
				.version("v1")
				.description("Spring Boot project built using **RESTful** api"+
				"Architecture, covers all the `basic CRUD operations`\n"
				+ "### Features:\n"
						
				+ "- Covers all crud operations \n"
				+ "- Performed field validations. \n"
				+ "- Used DTOs to control inbound and outbound data	\n"));
	
	// ** ** for bold
	// ` ` for code
	// - to make bullets and \n for next line.
	// # represents h1 tag, ##h2 , ###h3 and so on.
	}

}
