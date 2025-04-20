package com.example.userform.controller;

import org.springframework.web.bind.annotation.*;

@SuppressWarnings({"SameReturnValue", "unused"})
@RestController
@RequestMapping("/")
public class HelloController {

	@GetMapping("/admin/hello")
	public String adminHello() {
		return "Hello, Admin!";
	}

	@GetMapping("/user/hello")
	public String userHello() {
		return "Hello, Authenticated User!";
	}
}
