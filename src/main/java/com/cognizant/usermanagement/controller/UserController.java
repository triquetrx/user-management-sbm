package com.cognizant.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.usermanagement.dto.AuthenticationResponse;
import com.cognizant.usermanagement.dto.LoginDTO;
import com.cognizant.usermanagement.dto.RegistrationDTO;
import com.cognizant.usermanagement.dto.UpdateUserDTO;
import com.cognizant.usermanagement.exception.InvalidUserAccessException;
import com.cognizant.usermanagement.exception.UserCreationException;
import com.cognizant.usermanagement.exception.UserNotFoundException;
import com.cognizant.usermanagement.model.Message;
import com.cognizant.usermanagement.model.Users;
import com.cognizant.usermanagement.service.UserRequestService;

import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRequestService requestService;

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:5000")
	public ResponseEntity<?> loginPoint(@RequestBody LoginDTO loginDTO) {
		try {
			AuthenticationResponse loginUser = requestService.loginUser(loginDTO);
			return new ResponseEntity<>(new Message(200, "LOGIN_SUCCESSFULL", loginUser), HttpStatus.OK);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(new Message(errCode,"Invalid Credentials",message[5]), HttpStatus.valueOf(errCode));
		}
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:5000")
	public ResponseEntity<?> registrationPoint(@RequestBody RegistrationDTO registrationDTO) {
		try {
			String createUser = requestService.createUser(registrationDTO);
			return new ResponseEntity<>(new Message(200, createUser, "NEW_USER_CREATED"), HttpStatus.OK);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(message[5], HttpStatus.valueOf(errCode));
		} catch (Exception e) {
			return new ResponseEntity<>(new Message(400, e.getMessage(), "USER_CREATION_FAILED"),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	@CrossOrigin(origins = "http://localhost:5000")
	public ResponseEntity<?> updateUser(@RequestHeader(name = "Authorization") String token, @RequestBody UpdateUserDTO updateUserDTO) {
		try {
			Users result = requestService.updateUser(token, updateUserDTO);
			return new ResponseEntity<>(new Message(200,"User Updated",result),HttpStatus.OK);
		} catch (UserCreationException e) {
			return new ResponseEntity<>(new Message(400,e.getMessage(),null),HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(new Message(400,"USER_NOT_FOUND",null),HttpStatus.BAD_REQUEST);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(new Message(errCode,"AUTHORIZATION_ERROR",message[5]), HttpStatus.valueOf(errCode));
		}
	}
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:5000")
	public ResponseEntity<?> getUserById(@RequestHeader(name = "Authorization")String token,@PathVariable long id){
		try {
			Users result = requestService.getUserById(token, id);
			return new ResponseEntity<>(new Message(200, "USER_DATA_FOUND", result),HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(new Message(404, e.getMessage(), null),HttpStatus.NOT_FOUND);
		} catch (InvalidUserAccessException e) {
			return new ResponseEntity<>(new Message(401, "UNAUTHORIZED_ACCESS", null),HttpStatus.UNAUTHORIZED);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(new Message(errCode,"AUTHORIZATION_ERROR",message[5]), HttpStatus.valueOf(errCode));
		}
	}
	
	@GetMapping("/me")
	@CrossOrigin(origins = "http://localhost:5000")
	public ResponseEntity<?> getCurrentUserDetails(@RequestHeader(name = "Authorization")String token){
		try {
			Users myDetails = requestService.getMyDetails(token);
			return new ResponseEntity<>(new Message(200, "DETAILS_FOUND", myDetails),HttpStatus.OK);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(new Message(errCode,"AUTHORIZATION_ERROR",message[5]), HttpStatus.valueOf(errCode));
		}
	}
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:5000")
	public ResponseEntity<?> getAllUser(@RequestHeader(name="Authorization")String token){
		try {
			List<Users> result = requestService.getAllUsers(token);
			return new ResponseEntity<>(new Message(200, "USERS_DATA_FOUND", result),HttpStatus.OK);
		} catch (InvalidUserAccessException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new Message(401, "UNAUTHORIZED_ACCESS", null),HttpStatus.UNAUTHORIZED);
		} catch (FeignClientException e) {
			String[] message = e.getMessage().split(" ");
			int errCode = Integer.parseInt(message[0].split("")[1] + message[0].split("")[2] + message[0].split("")[3]);
			return new ResponseEntity<>(new Message(errCode,"AUTHORIZATION_ERROR",message[5]), HttpStatus.valueOf(errCode));
		}
	}

}