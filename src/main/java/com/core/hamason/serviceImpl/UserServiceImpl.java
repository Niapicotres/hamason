package com.core.hamason.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.core.hamason.data.model.User;
import com.core.hamason.data.repository.IUserRepository;
import com.core.hamason.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public Optional<User> findById(String username) {
		return this.userRepository.findById(username);
	}

	@Override
	public Set<User> findAll() {
		return new HashSet<User>(this.userRepository.findAll());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = this.findById(username);
		if (userOpt.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return userOpt.get();
	}
	
	@Override
	public User newEntity() {
		return new User();
	}

}
