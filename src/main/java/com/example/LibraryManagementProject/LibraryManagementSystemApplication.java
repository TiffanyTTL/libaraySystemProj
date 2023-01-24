package com.example.LibraryManagementProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**spring boot library management application.
 */


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@OpenAPIDefinition(info = @Info(title = "Library Management API", version = "2.0",
		description = "library management microservice"))
public class LibraryManagementSystemApplication  {
  public static void main(String[] args) {
    SpringApplication.run(LibraryManagementSystemApplication.class, args);
  }

}

