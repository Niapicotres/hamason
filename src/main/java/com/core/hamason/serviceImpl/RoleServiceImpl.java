package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.Role;
import com.core.hamason.service.IRoleService;
import com.core.hamason.data.repository.IRoleRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleServiceImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(String roleName) {
        return roleRepository.findById(roleName);
    }

    @Override
    public Set<Role> findAll() {
        return Set.copyOf(roleRepository.findAll());
    }
}

