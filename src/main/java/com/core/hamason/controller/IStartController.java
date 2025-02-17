package com.core.hamason.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.hamason.data.model.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public interface IStartController {
	
	public String loginGet(Principal principal, Model model, HttpServletRequest request);

	public String dumpDBGet();

	public String homeGet(Principal principal, Model model, HttpServletRequest request);

}
