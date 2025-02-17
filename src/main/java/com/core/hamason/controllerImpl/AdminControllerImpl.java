package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IAdminController;
import com.core.hamason.data.model.User;
import com.core.hamason.service.IUserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements IAdminController {

    @Autowired
    private IUserService userService;

    @GetMapping("/adminIndex")
    public String adminIndex(@RequestParam(value = "view", required = false) String view,
                             Model model) {

        // Si la URL contiene ?view=users, cargamos la lista de usuarios
        if ("users".equals(view)) {
            model.addAttribute("userList", userService.findAllUsers());
            model.addAttribute("showUsers", true);
        }

        return "admin/adminIndex";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("username") String username,
                             @RequestParam("fullname") String fullname,
                             @RequestParam("email") String email,
                             @RequestParam(value = "enabled", required = false) Boolean enabled) {
        // Buscar usuario en la base de datos
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            user.setFullname(fullname);
            user.setEmail(email);
            user.setEnabled(enabled != null); // Si es null, lo consideramos como "false"
            userService.updateUser(user);
        }
        return "redirect:/admin/adminIndex?view=users"; // Redirige a la lista actualizada
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam("username") String username, Model model) {
        User user = userService.findByUsername(username).orElse(new User());
        model.addAttribute("user", user);
        
        log.info("EDIT USER: " + user.getUsername());
        log.info("nombre USER: " + user);
        return "admin/editUsers"; 
    }

    // Método para eliminar usuario
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("username") String username) {
        userService.deleteUser(username);
        return "redirect:/admin/adminIndex?view=users";  // Redirige a la lista actualizada
    }
}
