package com.core.hamason.controller;

import com.core.hamason.data.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/users")
public interface IUserController {

    // Mostrar todos los usuarios
    String showAllUsers(Model model);

    // Mostrar formulario de edición de usuario
    String editUserForm(@PathVariable String username, Model model);

    // Guardar usuario editado
    String updateUser(User user);

    // Eliminar usuario
    String deleteUser(@PathVariable String username);
}

