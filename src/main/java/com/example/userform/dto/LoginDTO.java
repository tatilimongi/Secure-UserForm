package com.example.userform.dto;

import jakarta.validation.constraints.*;

@SuppressWarnings("unused")
public class LoginDTO {

	@NotBlank(message = "Email is required")
	@Pattern(
			regexp = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
			message = "Email format is invalid"
	)
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
			message = "Password must contain at least one uppercase letter and one digit, with no special characters"
	)
	private String password;

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}