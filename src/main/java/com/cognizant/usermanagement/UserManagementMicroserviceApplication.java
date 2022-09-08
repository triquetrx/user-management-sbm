package com.cognizant.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@EnableEurekaClient
@EnableSwagger2
@EnableAspectJAutoProxy
@SpringBootApplication
public class UserManagementMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementMicroserviceApplication.class, args);
	}

}
