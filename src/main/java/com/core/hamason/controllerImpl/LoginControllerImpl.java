package com.core.hamason.controllerImpl;

import com.core.hamason.controller.ILoginController;
import com.core.hamason.data.model.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
public class LoginControllerImpl implements ILoginController {

    @Override
    @GetMapping("/loginGet")
    public String loginGet(Principal principal, Model model, HttpServletRequest request) {
        log.info("TRAZA loginGet");
        model.addAttribute("login", new Login());
        return "standardLayouts/loginPage"; // Nombre de la plantilla HTML
    }
    
    
}
