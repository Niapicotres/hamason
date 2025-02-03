package com.core.hamason.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Role;
import com.core.hamason.data.repository.IRoleRepository;
import com.core.hamason.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		return this.roleRepository.save(role);
	}

	@Override
	public Optional<Role> findById(String rolename) {
		return this.roleRepository.findById(rolename);
	}

	@Override
	public Set<Role> findAll() {
		return new HashSet<Role>(this.roleRepository.findAll());
	}

}
