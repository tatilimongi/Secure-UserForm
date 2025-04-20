package com.example.userform.service;

import com.example.userform.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class UserService {

	private final List<UserDTO> users = new ArrayList<>();
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public boolean emailExists(String email) {
		return users.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
	}

	public void registerUser(UserDTO userDTO) {
		userDTO.setPassword(encoder.encode(userDTO.getPassword()));
		userDTO.setRole("USER");
		users.add(userDTO);
	}

	public List<UserDTO> getAllUsers() {
		return users;
	}
}
