package com.core.hamason.data.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Login {
	
	@Size(min=3, max=50, message="username must have from 3 to 50 characters")
	@Pattern(regexp = "[A-Za-z0-9_-]{3,50}", message="username must have only letters, numbers, _ and -")
	private String username;
	
	@Size(min=5, max=255, message="password must have from 5 to 255 characters")
	private String password;

}
