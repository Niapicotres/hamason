package com.core.hamason.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.hamason.data.model.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public interface ILoginController {
    // Carga el formulario de login
    String loginGet(Principal principal, Model model, HttpServletRequest request);

}
