package com.example.userform.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/protected")
@CrossOrigin(origins = "*")
public class ProtectedController {

	@GetMapping("/working-hours")
	public ResponseEntity<String> businessHoursOnly(HttpServletRequest request) {
		String role = (String) request.getAttribute("role");
		int hour = LocalTime.now().getHour();
		if (!"ADMIN".equalsIgnoreCase(role) || hour < 9 || hour > 18) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access allowed only during business hours");
		}
		return ResponseEntity.ok("You're in business hours, welcome!");
	}

	@GetMapping("/admin")
	public ResponseEntity<String> adminOnly(HttpServletRequest request) {
		String role = (String) request.getAttribute("role");
		if (role == null || !"ADMIN".equalsIgnoreCase(role)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: ADMIN only");
		}
		return ResponseEntity.ok("Welcome, Admin!");
	}

	@GetMapping("/user")
	public ResponseEntity<String> userOrAdmin(HttpServletRequest request) {
		String role = (String) request.getAttribute("role");
		if (role == null || (!"USER".equalsIgnoreCase(role) && !"ADMIN".equalsIgnoreCase(role))) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: USER or ADMIN only");
		}
		return ResponseEntity.ok("Welcome, User!");
	}
}
