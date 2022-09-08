package com.cognizant.usermanagement.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ModelTest {
	
	Message message = new Message();
	Users users = new Users();
	
	@Test
	void testMessage() {
		message.setMessage("This is a test");
		message.setPayload("Test");
		message.setStatus(200);
		
		assertEquals(message.getMessage(), "This is a test");
		assertEquals(message.getPayload(), "Test");
		assertEquals(message.getStatus(), 200);
	}
	
	@Test
	void testUsers() {
		users.setActive(true);
		users.setEmail("test@test.com");
		users.setGender("Female");
		users.setName("Tester test");
		users.setMobile(1234567890);
		users.setRole("ROLE_ADMIN");
		
		assertTrue(users.isActive());
		assertEquals(users.getEmail(), "test@test.com");
		assertEquals(users.getMobile(), 1234567890);
		assertEquals(users.getGender(), "Female");
		assertEquals(users.getRole(), "ROLE_ADMIN");
		assertEquals(users.getName(), "Tester test");
		
		
	}

}
