package com.example.userform.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/protected")
@CrossOrigin(origins = "*")
public class ProtectedController {

	private static final String ADMIN_ROLE = "ADMIN";

	@GetMapping("/working-hours")
	public ResponseEntity<String> businessHoursOnly(HttpServletRequest request) {
		String role = (String) request.getAttribute("role");
		int hour = LocalTime.now().getHour();
		if (!ADMIN_ROLE.equalsIgnoreCase(role) || hour < 9 || hour > 18) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access allowed only during business hours");
		}
		return ResponseEntity.ok("You're in business hours, welcome!");
	}

	@GetMapping("/admin")
	public ResponseEntity<String> adminOnly(HttpServletRequest request) {
		String role = (String) request.getAttribute("role");
		if (!ADMIN_ROLE.equalsIgnoreCase(role)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: ADMIN only");
		}
		return ResponseEntity.ok("Welcome, Admin!");
	}

	@GetMapping("/user")
	public ResponseEntity<String> userOrAdmin(HttpServletRequest request) {
		String role = (String) request.getAttribute("role");
		if (!"USER".equalsIgnoreCase(role) && !ADMIN_ROLE.equalsIgnoreCase(role)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: USER or ADMIN only");
		}
		return ResponseEntity.ok("Welcome, User!");
	}
}
