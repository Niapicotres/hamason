package com.core.hamason.service;

import com.core.hamason.data.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {

    // Crear usuario
    User createUser(User user);

    // Buscar usuario por username
    Optional<User> findByUsername(String username);

    // Listar todos los usuarios
    List<User> findAllUsers();

    // Actualizar usuario
    User updateUser(User user);

    // Eliminar usuario por username
    void deleteUser(String username);
}
