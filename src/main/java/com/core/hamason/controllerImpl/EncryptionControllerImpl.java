package com.core.hamason.controllerImpl;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.hamason.controller.IEncryptionController;
import com.core.hamason.data.model.Encryption;
import com.core.hamason.service.IEncryptionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EncryptionControllerImpl 
	extends MasterControllerImpl
	implements IEncryptionController {
	
	@Autowired
	private IEncryptionService encryptionService;

	
	@Override
	@PostMapping({"encryption/encryptionPost"})
	public String encryptionPost(
			@Valid Encryption encryption,
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA encryptionPost");
		if (bindingResult.hasErrors()) {
			log.error("El formulario de encryption tiene errores: " + bindingResult.getAllErrors());						
			this.injectCommonAttributesInHtmlPage(principal, model, request);
			return "redirect:/encryption/encryptionGet"; // + provider.getId();
		}
		else {
			log.info("Formulario correcto: " + encryption);
			encryption = this.encryptionService.encryptText(encryption);
			log.warn("TRAZA: después de salir de encryptionPost >> EncryptionService -> save");
			return "encryption/encryptionPost";
		}
	}

	@Override
	@GetMapping({"/encryption/encryptionGet"})
	public String encryptionGet(
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA encryptionGet");
		
		this.injectCommonAttributesInHtmlPage(principal, model, request);
		
		model.addAttribute("encryption", this.encryptionService.newEntity());
		model.addAttribute("encoderList", this.encryptionService.findAllEncodersName());
		
		return "encryption/encryptionGet";
	}



}
