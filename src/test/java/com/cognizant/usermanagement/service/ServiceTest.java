package com.cognizant.usermanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.usermanagement.dto.RegistrationDTO;
import com.cognizant.usermanagement.exception.AccountAlreadyExistsException;
import com.cognizant.usermanagement.exception.UserCreationException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

	@Autowired
	UserRequestService requestService;
	
	@Test
	void testLoadService() {
		assertThat(requestService).isNotNull();
	}
	
//	@Test
//	void addingUser() throws UserCreationException, AccountAlreadyExistsException {
//		String createUser = requestService.createUser(new RegistrationDTO("Test team", "test@test.com", "test", 123456789, new Date(), "MALE", null));
//		assertEquals("New user created", createUser);
//	}
	
}
