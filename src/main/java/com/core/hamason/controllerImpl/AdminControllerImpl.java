package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IAdminController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
public class AdminControllerImpl implements IAdminController {

    @Override
    @GetMapping("/admin/adminIndex")
    public String adminIndex(Principal principal, Model model, HttpServletRequest request) {
        log.info("Accediendo al panel de administración.");
        
        // Añade datos necesarios al modelo para la vista, si es necesario
        model.addAttribute("username", principal.getName());
        model.addAttribute("message", "Welcome to the Admin Dashboard");

        // Retorna la plantilla Thymeleaf ubicada en: src/main/resources/templates/admin/adminIndex.html
        return "admin/adminIndex";
    }
    @GetMapping("/admin/employeeIndex")
    public String employeeIndex(Principal principal, Model model, HttpServletRequest request) {
        model.addAttribute("username", principal.getName());
        return "admin/employeeIndex";
    }
}
