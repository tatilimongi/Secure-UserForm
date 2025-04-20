package com.example.userform.controller;

import com.example.userform.dto.LoginDTO;
import com.example.userform.dto.UserDTO;
import com.example.userform.service.UserService;
import com.example.userform.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	private final JwtUtil jwtUtil;
	private final UserService userService;

	public AuthController(JwtUtil jwtUtil, UserService userService) {
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			Optional<UserDTO> userOptional = userService.getAllUsers().stream()
					.filter(user -> user.getEmail().equalsIgnoreCase(loginDTO.getEmail()))
					.findFirst();

			if (userOptional.isPresent()) {
				UserDTO user = userOptional.get();

				if (encoder.matches(loginDTO.getPassword(), user.getPassword())) {
					String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
					Map<String, String> response = new HashMap<>();
					response.put("token", token);
					return ResponseEntity.ok(response);
				}
			}

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}
}
