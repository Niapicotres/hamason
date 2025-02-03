package com.core.hamason.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.core.hamason.data.model.User;

@Service
public interface IUserService extends UserDetailsService {
	
	public User save(User user);
	
	public Optional<User> findById(String username);
	
	public Set<User> findAll();

	public User newEntity();

}
