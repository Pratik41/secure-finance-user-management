package com.expense.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.expensetracker.dto.AuthRequest;
import com.expense.expensetracker.dto.AuthResponse;
import com.expense.expensetracker.entity.User;
import com.expense.expensetracker.repository.UserRepository;
import com.expense.expensetracker.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			return ResponseEntity.badRequest().body("Username already taken");
		}
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).body("Invalid credentials");
		}

		final String token = jwtUtil.generateToken(request.getUsername());
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	 @GetMapping("/allUsers")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = userRepository.findAll();
	        return ResponseEntity.ok(users);
	    }
}
