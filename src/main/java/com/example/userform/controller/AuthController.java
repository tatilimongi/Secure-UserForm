package com.example.userform.controller;

import com.example.userform.dto.LoginDTO;
import com.example.userform.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
		try {
			boolean userExists = UserController.getUsers().stream()
					.anyMatch(user -> user.getEmail().equalsIgnoreCase(loginDTO.getEmail()) &&
							user.getPassword().equals(loginDTO.getPassword()));

			if (userExists) {
				String token = jwtUtil.generateToken(loginDTO.getEmail());
				return ResponseEntity.ok(Collections.singletonMap("token", token));
			}

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Invalid credentials"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Internal Server Error"));
		}
	}
}
