package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.User;
import com.core.hamason.data.repository.IUserRepository;
import com.core.hamason.service.IUserService;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getUsername())) {
            throw new RuntimeException("Usuario no encontrado: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    // Carga un usuario para autenticación en Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

     
        
        List<GrantedAuthority> authorities = user.getRoleSet().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRolename()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(), authorities);
    }
}
