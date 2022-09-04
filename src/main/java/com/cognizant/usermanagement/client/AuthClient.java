package com.cognizant.usermanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.usermanagement.dto.AuthenticationResponse;
import com.cognizant.usermanagement.dto.LoginDTO;
import com.cognizant.usermanagement.dto.NewUserDTO;
import com.cognizant.usermanagement.dto.ValidatingDTO;

@FeignClient(name = "auth-microservice", url = "http://localhost:8001")
public interface AuthClient {

	@PostMapping("/authenticate")
	AuthenticationResponse createAuthentication(@RequestBody LoginDTO request);

	@GetMapping("/validate")
	ValidatingDTO validatingToken(@RequestHeader(name = "Authorization") String token);

	@PostMapping("/register")
	String addNewUser(@RequestBody NewUserDTO newUserDTO);

	@PutMapping("/update-user")
	String updateUser(@RequestHeader(name = "Authorization") String token, @RequestBody NewUserDTO newUserDTO);

}
