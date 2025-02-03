package com.core.hamason.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Role;

@Service
public interface IRoleService {
	
	public Role save(Role role);
	
	public Optional<Role> findById(String rolename);
	
	public Set<Role> findAll();

}
