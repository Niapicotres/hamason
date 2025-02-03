package com.core.hamason.controllerImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.core.hamason.config.LanguageResourceBundleMessage;
import com.core.hamason.controller.IMasterController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MasterControllerImpl implements IMasterController {
	
	@Autowired
	private LanguageResourceBundleMessage languageResourceBundleMessage; 

	@Override
	public void injectCommonAttributesInHtmlPage(
			Principal principal, 
			Model model, 
			HttpServletRequest request) {

		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");
		
		model.addAttribute("languageTagStringList", languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		model.addAttribute("requestURI", request.getRequestURI());
		
		log.warn("languageTagStringList: " + languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		log.warn("requestURI: ", request.getRequestURI());
		
	}

}
