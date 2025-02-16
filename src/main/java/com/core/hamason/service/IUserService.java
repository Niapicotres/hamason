package com.core.hamason.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.core.hamason.data.model.User;

public interface IUserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserByUsername(String username);
    void saveUser(User user);
    void deleteUser(String username);
}