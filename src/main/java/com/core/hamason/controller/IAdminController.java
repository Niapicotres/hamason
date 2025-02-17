package com.core.hamason.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public interface IAdminController {
    
    // Carga la página principal del panel de administración
    String adminIndex(@RequestParam(value = "view", required = false) String view, 
            Model model);
    
   
   
    
}
