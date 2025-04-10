package com.example.userform.controller;

import com.example.userform.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*")
public class UserController {

	private static final List<UserDTO> users = new ArrayList<>();

	public static List<UserDTO> getUsers() {
		return users;
	}

	@PostMapping(consumes = "application/json", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {

		boolean emailExists = users.stream()
				.anyMatch(user -> user.getEmail().equalsIgnoreCase(userDTO.getEmail()));

		if (emailExists) {
			return ResponseEntity.badRequest().body("Email already registered");
		}

		users.add(userDTO);
		return ResponseEntity.ok("User registered successfully");
	}
}
