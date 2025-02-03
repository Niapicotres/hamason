package com.core.hamason.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.hamason.data.model.Encryption;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public interface IEncryptionController {

	public String encryptionGet(
			Principal principal, 
			Model model, 
			HttpServletRequest request);

	

	public String encryptionPost(
			@Valid Encryption encryption, 
			BindingResult bindingResult, 
			Principal principal, 
			Model model,
			HttpServletRequest request);


}
