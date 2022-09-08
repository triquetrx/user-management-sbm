package com.cognizant.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @AllArgsConstructor @NoArgsConstructor class UpdateUserDTO {

	private String name;
	private String email;
	private long mobile;
	private String role;
	private boolean active;
	
}
