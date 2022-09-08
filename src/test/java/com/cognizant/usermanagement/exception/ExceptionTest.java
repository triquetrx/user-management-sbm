package com.cognizant.usermanagement.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExceptionTest {
	
	AccountAlreadyExistsException accountAlreadyExistsException;
	InvalidUserAccessException invalidUserAccessException;
	UserCreationException creationException;
	UserNotFoundException notFoundException;
	
	@Test
	void testAccountAlreadyExistsExceptionLoad() {
		assertThat(accountAlreadyExistsException).isNull();
	}
	
	@Test
	void testInvalidUserAccessExceptionLoad() {
		assertThat(invalidUserAccessException).isNull();
	}
	
	@Test
	void testCreationExceptionLoad() {
		assertThat(creationException).isNull();
	}
	
	@Test
	void testNotFoundException() {
		assertThat(notFoundException).isNull();
	}
	

}
