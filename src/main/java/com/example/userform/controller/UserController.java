package com.example.userform.controller;

import com.example.userform.dto.UserDTO;
import com.example.userform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(consumes = "application/json", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {

		if (userService.emailExists(userDTO.getEmail())) {
			return ResponseEntity.badRequest().body("Email already registered");
		}

		userService.registerUser(userDTO);
		return ResponseEntity.ok("User registered successfully");
	}
}