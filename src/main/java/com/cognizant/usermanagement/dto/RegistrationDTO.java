package com.cognizant.usermanagement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @AllArgsConstructor @Data @NoArgsConstructor class RegistrationDTO {
	
	private String name;
	private String email;
	private String password;
	private long mobile;
	private Date birthDate;
	private String gender;
	private String role;

}
