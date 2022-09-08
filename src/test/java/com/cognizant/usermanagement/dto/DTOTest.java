package com.cognizant.usermanagement.dto;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DTOTest {

	AuthenticationResponse authenticationResponse;
	LoginDTO loginDTO;
	NewUserDTO newUserDTO;
	RegistrationDTO registrationDTO;
	UpdateUserDTO updateUserDTO;
	ValidatingDTO validatingDTO;

	@Test
	void testAuthenticationResponseDTO() {
		authenticationResponse = new AuthenticationResponse("TOKEN", "ZAID");
		assertEquals(authenticationResponse.getToken(), "TOKEN");
		assertEquals(authenticationResponse.getName(), "ZAID");
	}

	@Test
	void testLoginDTO() {
		loginDTO = new LoginDTO("test@test.com", "test");
		assertEquals(loginDTO.getEmail(), "test@test.com");
		assertEquals(loginDTO.getPassword(), "test");
	}

	@Test
	void testNewUserDTO() {
		newUserDTO = new NewUserDTO("Test","test@test.com","test", (long) 123456,"ROLE_TEST","SECURITY");
		assertEquals(newUserDTO.getName(), "Test");
		assertEquals(newUserDTO.getEmail(), "test@test.com");
		assertEquals(newUserDTO.getPassword(), "test");
		assertEquals(newUserDTO.getUserId(), 123456);
		assertEquals(newUserDTO.getRole(), "ROLE_TEST");
		assertEquals(newUserDTO.getSecurityKey(), "SECURITY");
		
	}

	@Test
	void testRegistrationDTO() {
		registrationDTO = new RegistrationDTO("Test team", "test@test.com", "test", 123456789, new Date(), "MALE", "ROLE_TEST");
		assertEquals(registrationDTO.getName(), "Test team");
		assertEquals(registrationDTO.getEmail(),"test@test.com");
		assertEquals(registrationDTO.getPassword(),"test");
		assertEquals(registrationDTO.getMobile(),123456789);
		assertEquals(registrationDTO.getGender(),"MALE");
		assertEquals(registrationDTO.getRole(), "ROLE_TEST");
	}
	
	@Test
	void testValidatingDTO() {
		validatingDTO = new ValidatingDTO(true, "ROLE_TEST", "test@test.com");
		assertEquals(validatingDTO.getEmail(), "test@test.com");
		assertEquals(validatingDTO.getUserRole(), "ROLE_TEST");
		assertTrue(validatingDTO.isValidStatus());
	}
	
	@Test
	void testUpdateDTO() {
		updateUserDTO = new UpdateUserDTO("test", "test@test.com", 12345678, "ROLE_TEST", true);
		assertTrue(updateUserDTO.isActive());
		assertEquals(updateUserDTO.getEmail(), "test@test.com");
		assertEquals(updateUserDTO.getName(), "test");
		assertEquals(updateUserDTO.getMobile(), 12345678);
		assertEquals(updateUserDTO.getRole(),"ROLE_TEST");
	}

}
