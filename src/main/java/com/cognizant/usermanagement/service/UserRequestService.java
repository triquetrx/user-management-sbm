package com.cognizant.usermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.usermanagement.dto.AuthenticationResponse;
import com.cognizant.usermanagement.dto.LoginDTO;
import com.cognizant.usermanagement.dto.RegistrationDTO;
import com.cognizant.usermanagement.dto.UpdateUserDTO;
import com.cognizant.usermanagement.exception.AccountAlreadyExistsException;
import com.cognizant.usermanagement.exception.InvalidUserAccessException;
import com.cognizant.usermanagement.exception.UserCreationException;
import com.cognizant.usermanagement.exception.UserNotFoundException;
import com.cognizant.usermanagement.model.Users;

@Service
public interface UserRequestService {

	String createUser(RegistrationDTO registrationDTO) throws UserCreationException, AccountAlreadyExistsException;

	Users updateUser(String token, UpdateUserDTO userDTO) throws UserCreationException, UserNotFoundException;

	AuthenticationResponse loginUser(LoginDTO loginDTO);

	Users getMyDetails(String token);

	Users getUserById(String token, long id) throws UserNotFoundException, InvalidUserAccessException;

	List<Users> getAllUsers(String token) throws InvalidUserAccessException;

	

}
