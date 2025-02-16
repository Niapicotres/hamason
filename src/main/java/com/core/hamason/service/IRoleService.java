package com.core.hamason.service;

import java.util.Optional;
import java.util.Set;
import com.core.hamason.data.model.Role;

public interface IRoleService {
	
    Role save(Role role);
	
    Optional<Role> findById(String rolename);
	
    Set<Role> findAll();
}

