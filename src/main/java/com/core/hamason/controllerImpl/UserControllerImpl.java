package com.core.hamason.controllerImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.core.hamason.data.model.User;
import com.core.hamason.service.IUserService;
import com.core.hamason.controller.IUserController;

@Controller
public class UserControllerImpl implements IUserController {
    
    @Autowired
    private IUserService userService;

    @Override
    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}