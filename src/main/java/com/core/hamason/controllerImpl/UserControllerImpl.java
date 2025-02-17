package com.core.hamason.controllerImpl;

import com.core.hamason.data.model.User;
import com.core.hamason.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserControllerImpl {

    @Autowired
    private IUserService userService;

    // Mostrar todos los usuarios en el index
    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/userIndex"; // Vista Thymeleaf para listar usuarios
    }

    // Editar usuario (mostrar formulario)
    @GetMapping("/edit/{username}")
    public String editUserForm(@PathVariable String username, Model model) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "admin/editUser"; // Formulario para editar usuario
        } else {
            return "redirect:/users?error=notfound";
        }
    }

    // Guardar cambios en usuario editado
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users?success=updated";
    }

    // Eliminar usuario
    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return "redirect:/users?success=deleted";
    }
}
