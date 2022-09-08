package com.cognizant.usermanagement.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.usermanagement.client.AuthClient;
import com.cognizant.usermanagement.dto.AuthenticationResponse;
import com.cognizant.usermanagement.dto.LoginDTO;
import com.cognizant.usermanagement.dto.NewUserDTO;
import com.cognizant.usermanagement.dto.RegistrationDTO;
import com.cognizant.usermanagement.dto.UpdateUserDTO;
import com.cognizant.usermanagement.dto.ValidatingDTO;
import com.cognizant.usermanagement.exception.AccountAlreadyExistsException;
import com.cognizant.usermanagement.exception.InvalidUserAccessException;
import com.cognizant.usermanagement.exception.UserCreationException;
import com.cognizant.usermanagement.exception.UserNotFoundException;
import com.cognizant.usermanagement.model.Users;
import com.cognizant.usermanagement.repository.UserRepository;
import com.cognizant.usermanagement.service.UserRequestService;

@Service
public class UserRequestServiceImpl implements UserRequestService {

	@Autowired
	UserRepository repository;

	@Autowired
	AuthClient authClient;

	@Override
	@Transactional
	public String createUser(RegistrationDTO registrationDTO) throws UserCreationException, AccountAlreadyExistsException {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		Optional<Users> findByEmail = repository.findByEmail(registrationDTO.getEmail());
		if(findByEmail.isEmpty()) {			
			Users users = repository.save(new Users(registrationDTO.getName(), registrationDTO.getEmail(),
					registrationDTO.getMobile(), date, registrationDTO.getBirthDate(), registrationDTO.getGender(), "ROLE_USER", true));
			String addNewUser = authClient.addNewUser(new NewUserDTO(users.getName(), users.getEmail(),
					registrationDTO.getPassword(), users.getId(), users.getRole(),
					(format.format(date) + users.getEmail() + format.format(registrationDTO.getBirthDate()))));
			if (addNewUser.equalsIgnoreCase("New User Created")) {
				return "New user created";
			} else {
				repository.delete(users);
				throw new UserCreationException("USER_CAN_NOT_BE_CREATED");
			}
		}
		throw new AccountAlreadyExistsException("Account already present");

	}

	@Override
	@Transactional
	public Users updateUser(String token, UpdateUserDTO userDTO) throws UserCreationException, UserNotFoundException {

		ValidatingDTO validatingToken = authClient.validatingToken(token);
		Users users = null;
		if (validatingToken.getUserRole().equalsIgnoreCase("ROLE_ADMIN")) {
			if (repository.findByEmail(userDTO.getEmail()).isPresent()) {
				users = repository.findByEmail(userDTO.getEmail()).get();
				users.setRole(userDTO.getRole());
			} else {
				throw new UserNotFoundException("USER_NOT_FOUND");
			}
		} else {
			users = repository.findByEmail(validatingToken.getEmail()).get();
			userDTO.setRole(users.getRole());
		}
		users.setMobile(userDTO.getMobile());
		users.setName(userDTO.getName());


		String authClientResult = authClient.updateUser(token, new NewUserDTO(userDTO.getName(), userDTO.getEmail(),
				"", users.getId(), users.getRole(), ""));
		if (authClientResult.equalsIgnoreCase("USER_UPDATED")) {
			Users userResult = repository.save(users);
			return userResult;
		} else {
			throw new UserCreationException("USER_CAN_NOT_BE_UPDATED_INTERNAL_ERROR");
		}
	}

	@Override
	@Transactional
	public AuthenticationResponse loginUser(LoginDTO loginDTO) {
		AuthenticationResponse response = authClient.createAuthentication(loginDTO);
		return response;
	}
	
	@Override
	@Transactional
	public Users getMyDetails(String token) {
		ValidatingDTO validator = authClient.validatingToken(token);
		return repository.findByEmail(validator.getEmail()).get();
	}

	@Override
	@Transactional
	public Users getUserById(String token, long id) throws UserNotFoundException, InvalidUserAccessException {
		ValidatingDTO validator = authClient.validatingToken(token);
		Optional<Users> users = repository.findById(id);
		if (users.isPresent()) {
			if (validator.isValidStatus() && users.get().getEmail().equalsIgnoreCase(validator.getEmail())
					&& !validator.getUserRole().contains("ROLE_ADMIN")) {
				return users.get();
			} else if (validator.isValidStatus() && validator.getUserRole().contains("ROLE_ADMIN")) {
				return users.get();
			} else {
				throw new InvalidUserAccessException("UNAUTHORIZED_ACCESS");
			}
		} else {
			throw new UserNotFoundException("USER_NOT_FOUND");
		}
	}

	@Override
	@Transactional
	public List<Users> getAllUsers(String token) throws InvalidUserAccessException {
		if (authClient.validatingToken(token).isValidStatus()
				&& authClient.validatingToken(token).getUserRole().contains("ROLE_ADMIN")) {
			return repository.findAll();
		}
		throw new InvalidUserAccessException("UNAUTHORIZED_DATA_ACCESS");
	}
	
}
