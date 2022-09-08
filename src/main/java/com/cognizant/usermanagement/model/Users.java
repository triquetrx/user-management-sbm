package com.cognizant.usermanagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @NoArgsConstructor class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull(message = "Name can not be null")
	private String name;
	@NotNull(message = "Email can not be null")
	@Pattern(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+[.]{1}[a-z]{1,3}", message = "Email must be in style abc@xyz.com")
	private String email;
	@NotNull(message = "Mobile number can not be null")
	private long mobile;
	private Date registrationDate;
	private Date birthDate;
	private String gender;
	private String role;
	private boolean active;

	public Users(@NotNull(message = "Name can not be null") String name,
			@NotNull(message = "Email can not be null") @Pattern(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+[.]{1}[a-z]{1,3}", message = "Email must be in style abc@xyz.com") String email,
			@NotNull(message = "Mobile number can not be null") long mobile, Date registrationDate, Date birthDate, String gender,String role,
			boolean active) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.birthDate = birthDate;
		this.registrationDate = registrationDate;
		this.gender = gender;
		this.role = role;
		this.active = active;
	}

}
