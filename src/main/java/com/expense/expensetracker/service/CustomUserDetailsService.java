package com.expense.expensetracker.service;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expense.expensetracker.entity.User;
import com.expense.expensetracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		// map roles (comma-separated) to authorities
		return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(),
				Arrays.stream(u.getRoles().split(",")).map(String::trim).map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList()));
	}
}
