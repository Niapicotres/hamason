package com.core.hamason.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.core.hamason.data.model.User;
import com.core.hamason.service.IUserService;

@Controller
public interface IUserController {
    
    @GetMapping("/users")
    String showUsers(Model model);
}
