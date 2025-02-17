package com.core.hamason.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public interface IAdminController {
    
    // Carga la página principal del panel de administración
    String adminIndex(Principal principal, Model model, HttpServletRequest request);
    String employeeIndex(Principal principal, Model model, HttpServletRequest request);
    
}
